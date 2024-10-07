async function fetchFiles() {
    try {

        const response = await fetch('https://api.github.com/repos/sagarpatel288/kotlinDSAWithIntelliJIdea/contents');

        // Check if response is successful
        if (!response.ok) {
            throw new Error('Failed to fetch repository content');
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
