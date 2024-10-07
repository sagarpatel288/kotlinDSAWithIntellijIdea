document.addEventListener('DOMContentLoaded', async () => {
    const fileList = document.getElementById('file-list');
    const sortOptions = document.getElementById('sort-options');
    
    const username = 'sagarpatel288';
    const repo = 'kotlinDSAWithIntellijIdea';
    let kotlinFiles = [];

    // Fetch all Kotlin files recursively from the repository
    async function fetchKotlinFiles(path = '') {
        try {
            const response = await fetch(`https://api.github.com/repos/${username}/${repo}/contents/${path}`);
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            const data = await response.json();

            if (Array.isArray(data)) {
                for (const item of data) {
                    if (item.type === 'dir') {
                        // Recursively fetch Kotlin files in subdirectories
                        await fetchKotlinFiles(item.path);
                    } else if (item.type === 'file' && item.name.endsWith('.kt')) {
                        kotlinFiles.push({ name: item.name, url: item.html_url, date: new Date(item.git_url) });
                    }
                }
            } else {
                console.error('Unexpected data format:', data);
            }
        } catch (error) {
            console.error('Error fetching file list:', error);
        }
    }

    // Render the list of Kotlin files
    function renderFileList(files) {
        fileList.innerHTML = '';
        files.forEach(file => {
            const link = document.createElement('a');
            link.href = file.url;
            link.className = 'file-link';
            link.textContent = file.name;
            fileList.appendChild(link);
        });
    }

    // Sort and render the file list based on selected option
    sortOptions.addEventListener('change', () => {
        const option = sortOptions.value;
        let sortedFiles = [...kotlinFiles];

        switch (option) {
            case 'ascending':
                sortedFiles.sort((a, b) => a.name.localeCompare(b.name));
                break;
            case 'descending':
                sortedFiles.sort((a, b) => b.name.localeCompare(a.name));
                break;
            case 'latest':
                sortedFiles.sort((a, b) => b.date - a.date);
                break;
            case 'oldest':
                sortedFiles.sort((a, b) => a.date - b.date);
                break;
            default:
                break;
        }

        renderFileList(sortedFiles);
    });

    // Start fetching Kotlin files from the root of the repository
    await fetchKotlinFiles();
    renderFileList(kotlinFiles);
});