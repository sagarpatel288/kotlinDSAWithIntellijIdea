/**
* contentListUpdater.js (Node.js environment)
* This script should run in a Node.js environment (like GitHub Actions) to fetch data from the GitHub API
* and create files.json.
* It uses fs to write files.json and process.env.GITHUB_TOKEN for authentication.
* Environment Separation: Ensure server-side code runs in GitHub Actions and client-side code runs in the browser.
* Sorting and UI: Handle sorting and UI interactions in the browser.
* Loading and Messages: Use the browser script to manage loading states and display messages.
* This separation ensures that each part of your application runs in the correct environment without errors.
*/
import fs from 'fs'; // Use 'import' instead of 'require'
import fetch from 'node-fetch';  // Use 'import' instead of 'require'

(async () => {
  const username = 'sagarpatel288';
  const repo = 'kotlinDSAWithIntellijIdea';
  const accessToken = process.env.GITHUB_TOKEN;
  let kotlinFiles = [];

  async function fetchKotlinFiles(path = '') {
    try {
      const headers = {
        Authorization: `Bearer ${accessToken}`
      };
      const response = await fetch(`https://api.github.com/repos/${username}/${repo}/contents/${path}?t=${new Date().getTime()}`, { headers });
      if (!response.ok) {
        throw new Error(`Network response was not ok for path: ${path}`);
      }

      const data = await response.json();
      console.log('Fetched Data:', data); // Log fetched data
      if (Array.isArray(data)) {
        for (const item of data) {
          console.log('Processing Item:', item); // Log each item being processed
          if (item.type === 'dir') {
            await fetchKotlinFiles(item.path);
          } else if (item.type === 'file' && item.name.endsWith('.kt')) {
            kotlinFiles.push({ name: item.name, url: item.html_url });
          }
        }
      }
    } catch (error) {
      console.error(`Error fetching file list for path ${path}:`, error);
    }
  }

  try {
    await fetchKotlinFiles();
    fs.writeFileSync('files.json', JSON.stringify(kotlinFiles, null, 2));
    console.log('files.json has been saved with the latest Kotlin files.');
  } catch (error) {
    console.error('Error writing to files.json:', error);
  }
})();