document.addEventListener('DOMContentLoaded', async () => {
    const fileList = document.getElementById('file-list');
    const sortOptions = document.getElementById('sort-options');
    const loader = document.getElementById('loader');
    const messageBox = document.getElementById('message-box');
    const messageText = document.getElementById('message-text');
    const closeMessageButton = document.getElementById('close-message');

    let kotlinFiles = [];

    // Show the loader until the files are loaded
    loader.style.display = 'block';

    // Close the message box when the close button is clicked
    closeMessageButton.addEventListener('click', () => {
        messageBox.style.display = 'none';
    });

    async function loadFiles() {
        try {
            const timestamp = new Date().getTime();  // Prevent caching
            const response = await fetch(`files.json`);
            if (!response.ok) throw new Error('Failed to fetch files');

            kotlinFiles = await response.json();
            renderFileList(kotlinFiles);
        } catch (error) {
            console.error('Error loading files:', error);
            showMessage('Error loading files. Please try again later.', 'error');
        } finally {
            loader.style.display = 'none';
        }
    }

    function renderFileList(files) {
        fileList.innerHTML = '';
        files.forEach(file => {
            const link = document.createElement('a');
            link.href = file.url;
            link.textContent = file.name;
            link.className = 'file-link';
            fileList.appendChild(link);
        });
    }

    function showMessage(message, type) {
        messageText.textContent = message;
        messageBox.className = `message-box ${type}`;
        messageBox.style.display = 'block';
    }

    // Add sorting functionality
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
                sortedFiles.sort((a, b) => new Date(b.date) - new Date(a.date));
                break;
            case 'oldest':
                sortedFiles.sort((a, b) => new Date(a.date) - new Date(b.date));
                break;
            default:
                break;
        }

        renderFileList(sortedFiles);
    });

    await loadFiles();
});