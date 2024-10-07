async function fetchFiles() {
    try {

        const response = await fetch('https://api.github.com/repos/sagarpatel288/kotlinDSAWithIntelliJIdea/contents');

        // Check if response is successful
        if (!response.ok) {
            throw new Error('Failed to fetch repository content');
        } else {
            console.log("Success!")
        }

        const data = await response.json();
        const fileList = document.getElementById('file-list');

        // Filter Kotlin files and sort them by name
        const ktFiles = data.filter(item => item.name.endsWith('.kt'));
        ktFiles.sort((a, b) => a.name.localeCompare(b.name));

        // Create links for each Kotlin file
        ktFiles.forEach(file => {
            const fileLink = document.createElement('a');
            fileLink.href = file.html_url; // GitHub link to the file
            fileLink.textContent = file.name;
            fileLink.target = '_blank';
            fileList.appendChild(fileLink);
            fileList.appendChild(document.createElement('br'));
        });
    } catch (error) {
        console.error('Error fetching files:', error);
        document.getElementById('file-list').textContent = 'Error loading files.';
    }
}

// Execute the function to fetch and display files
fetchFiles();

document.addEventListener('DOMContentLoaded', async () => {
    const fileList = document.getElementById('file-list');

    const username = 'sagarpatel288';
    const repo = 'kotlinDSAWithIntellijIdea';

    // Fetch all Kotlin files recursively from the repository
    async function fetchKotlinFiles(path = '') {
        try {
            const response = await fetch(`https://api.github.com/repos/${username}/${repo}/contents/${path}`);
            const data = await response.json();

            for (const item of data) {
                if (item.type === 'dir') {
                    // Recursively fetch Kotlin files in subdirectories
                    await fetchKotlinFiles(item.path);
                } else if (item.type === 'file' && item.name.endsWith('.kt')) {
                    // Create a link element for Kotlin files
                    const link = document.createElement('a');
                    link.href = item.html_url;
                    link.className = 'file-link';
                    link.textContent = item.name;
                    fileList.appendChild(link);
                }
            }
        } catch (error) {
            console.error('Error fetching file list:', error);
        }
    }

    // Start fetching Kotlin files from the root of the repository
    await fetchKotlinFiles();
});

