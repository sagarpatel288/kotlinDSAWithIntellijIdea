/**
 * This is the browser script. It runs when we launch our website (GitHub pages website).
 * This script is a companion of index.html to render the data on our website.
 * This function attaches an event listener to the document.
 * The object `document` is a global object representing the entire HTML document.
 * The `.addEventListener('DOMContentLoaded', ...)` is a method used to listen for a specific event
 * (DOMContentLoaded in this case).
 * The 'DOMContentLoaded' event is fired when the initial HTML document has been completely loaded and parsed.
 * It does not wait for stylesheets, images, or other external resources to load—just the core HTML structure.
 * Using this event ensures that the DOM (Document Object Model) is fully available,
 * and all the elements are accessible for JavaScript manipulation.
 * The `async () => { }` is an arrow function that is declared as async.
 * The async keyword allows you to use await within the function, which makes it easier to work with asynchronous code.
 * This type of function is called an asynchronous immediately-invoked function in this context because
 * it is executed as soon as the event occurs.
 * It conveys:
 * "Once the HTML document is fully loaded, execute the asynchronous function defined inside the { ... } block."
 */
document.addEventListener('DOMContentLoaded', async () => {
    // Retrieve references to HTML elements by their ID
    const fileList = document.getElementById('file-list');
    const sortOptions = document.getElementById('sort-options');
    const loader = document.getElementById('loader');
    const messageBox = document.getElementById('message-box');
    const messageText = document.getElementById('message-text');
    const closeMessageButton = document.getElementById('close-message');

    /*
     * Create an empty array that will store the external json information that we are going to fetch.
     * The external json information will be stored temporarily (within this function scope only) in this array.
     */
    let kotlinFiles = [];

    // Show the loader until the files are loaded
    loader.style.display = 'block';

    // Close the message box when the close button is clicked
    closeMessageButton.addEventListener('click', () => {
        messageBox.style.display = 'none';
    });

    async function loadFiles() {
        try {
            // This line makes a fetch request to retrieve the files.json file.
            // The await keyword makes the function pause until the fetch request completes,
            // allowing you to work with the result as if it were synchronous.
            const response = await fetch(`files.json`);
            if (!response.ok) throw new Error('Failed to fetch files');

            // This line parses the response from the files.json file into a JavaScript object or array
            // and stores it in the variable kotlinFiles.
            // The `await` keyword ensures that the code waits until the data is fully parsed.
            kotlinFiles = await response.json();

            // The function `renderFileList` is used to dynamically display the list of Kotlin files on the page,
            // rendering them as links or in another UI format.
            renderFileList(kotlinFiles);
        } catch (error) {
            console.error('Error loading files:', error);
            showMessage('Error loading files. Please try again later.', 'error');
        } finally {
            loader.style.display = 'none';
        }
    }

    /*
     * Why this function? To render the data (files) on browser UI.
     * How does it do that?
     * The function takes a single parameter called files, which is expected to be an array of file objects.
     * Each object contains details such as the file name and URL.
     * Within the function:
     * The `fileList` is a reference to an HTML element (e.g., a div) that has the ID file-list.
     * It is where the list of files will be displayed on the page.
     * Setting innerHTML to an empty string ('') clears any existing content within the fileList element.
     * This ensures that each time the function runs, the file list is refreshed with only the latest set of files, avoiding duplicates.
     * Then, we create a new anchor (<a>) element dynamically.
     * An anchor element is used to create clickable links in HTML.
     * link.href = file.url; Sets the href attribute of the newly created anchor (<a>) element to the URL provided by the file object.
     * This makes each link navigable, allowing the user to click on it to access the specific file URL.
     * The file.name is displayed as the clickable text of the link.
     * link.className = 'file-link';
     * Adds a CSS class ('file-link') to the link element.
     * This allows you to style the links uniformly using CSS. It provides consistency in appearance and behavior.
     * fileList.appendChild(link);:
     * fileList is the HTML element where you want to display the list of files.
     * appendChild(link) adds the newly created anchor (<a>) element to the fileList container.
     * This line is responsible for adding the link to the DOM so that it becomes visible on the webpage.
     */
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