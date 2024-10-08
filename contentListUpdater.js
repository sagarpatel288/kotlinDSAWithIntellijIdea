document.addEventListener('DOMContentLoaded', async () => {
    const fileList = document.getElementById('file-list');
    const sortOptions = document.getElementById('sort-options');
    const loader = document.getElementById('loader');
    const messageBox = document.getElementById('message-box');
    const messageText = document.getElementById('message-text');
    const closeMessageButton = document.getElementById('close-message');

    const username = 'sagarpatel288';
    const repo = 'kotlinDSAWithIntellijIdea';
    let kotlinFiles = [];

    // Show loader initially
    loader.style.display = 'block';

    // Close message box
    closeMessageButton.addEventListener('click', () => {
        messageBox.style.display = 'none';
    });

    // Fetch all Kotlin files recursively from the repository
    async function fetchKotlinFiles(path = '') {
        try {
            const response = await fetch(`https://api.github.com/repos/${username}/${repo}/contents/${path}?t=${new Date().getTime()}`, {
                                    headers: {
                                        'Authorization': `Bearer ${accessToken}`, // Ensure token is added if required
                                    },
                                });
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            const data = await response.json();
            console.log('Fetched Data:', data); // Log fetched data
            if (Array.isArray(data)) {
                for (const item of data) {
                    console.log('Processing Item:', item); // Log each item being processed
                    if (item.type === 'dir') {
                        // Recursively fetch Kotlin files in subdirectories
                        await fetchKotlinFiles(item.path);
                    } else if (item.type === 'file' && item.name.endsWith('.kt')) {
                        kotlinFiles.push({ name: item.name, url: item.html_url, date: new Date() });
                    }
                }
            } else {
                console.error('Unexpected data format:', data);
            }
        } catch (error) {
            console.error('Error fetching file list:', error);
            showMessage('Error fetching file list. Please try again later.', 'error');
        } finally {
            // Hide loader after fetching is done
            loader.style.display = 'none';
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

    // Show message box
    function showMessage(message, type) {
        messageText.textContent = message;
        messageBox.className = `message-box ${type}`;
        messageBox.style.display = 'block';
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
    // Write fetched Kotlin files to files.json
    try {
        fs.writeFileSync('files.json', JSON.stringify(kotlinFiles, null, 2));
        console.log('files.json has been saved with the latest Kotlin files.');
    } catch (error) {
        console.error('Error writing to files.json:', error);
    }
})();