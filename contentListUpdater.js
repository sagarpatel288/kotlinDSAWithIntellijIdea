const fs = require('fs');
const fetch = require('node-fetch');

(async () => {
    const username = 'sagarpatel288';
    const repo = 'kotlinDSAWithIntellijIdea';
    const accessToken = process.env.GITHUB_TOKEN;

    let kotlinFiles = [];

    async function fetchKotlinFiles(path = '') {
        try {
            const headers = {
                Authorization: `token ${accessToken}`
            };
            const response = await fetch(`https://api.github.com/repos/${username}/${repo}/contents/${path}`, { headers });
            if (!response.ok) {
                throw new Error(`Network response was not ok for path: ${path}`);
            }
            const data = await response.json();

            if (Array.isArray(data)) {
                for (const item of data) {
                    if (item.type === 'dir') {
                        // Recursively fetch Kotlin files in subdirectories
                        await fetchKotlinFiles(item.path);
                    } else if (item.type === 'file' && item.name.endsWith('.kt')) {
                        kotlinFiles.push({ name: item.name, url: item.html_url, date: item.git_url });
                    }
                }
            }
        } catch (error) {
            console.error(`Error fetching file list for path ${path}:`, error);
        }
    }

    // Start fetching Kotlin files
    await fetchKotlinFiles();

    // Write fetched Kotlin files to files.json
    try {
        fs.writeFileSync('files.json', JSON.stringify(kotlinFiles, null, 2));
        console.log('files.json has been saved with the latest Kotlin files.');
    } catch (error) {
        console.error('Error writing to files.json:', error);
    }
})();