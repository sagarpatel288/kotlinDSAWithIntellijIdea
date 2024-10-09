/**
* This is the script that will run on GitHub. This is not the browser script.
* This script prepares (creates, generates) the data.
* This script should run in a Node.js environment (like GitHub Actions) to fetch data from the GitHub API
* and create files.json.
* It uses fs to write files.json and process.env.GITHUB_TOKEN for authentication.
* Environment Separation: Ensure server-side code runs in GitHub Actions and client-side code runs in the browser.
* Sorting and UI: Handle sorting and UI interactions in the browser.
* Loading and Messages: Use the browser script to manage loading states and display messages.
* This separation ensures that each part of your application runs in the correct environment without errors.
*/
// Import necessary modules. Use 'import' instead of 'require'.
import fs from 'fs';
import fetch from 'node-fetch';

/*
 * The expression (async () => { ... })() is an Immediately Invoked Async Function Expression (IIAFE).
 * The keyword async indicates that the function is asynchronous.
 * Asynchronous functions return a Promise and allow you to
 * use the await keyword inside them to pause execution until a Promise is resolved or rejected.
 * The parentheses around async () => { ... } create a function expression.
 * This function is called immediately after being defined, hence the term Immediately Invoked Function Expression (IIFE).
 * This allows you to execute asynchronous code immediately without explicitly naming the function or invoking it separately.
 * Anonymous Arrow Function `() => {}`` is a syntax, that defines a function using the arrow function syntax,
 * which is a more concise way of writing functions in JavaScript.
 * The IIFE pattern is used to create a scope for the variables declared inside the function.
 * This prevents polluting the global scope.
 * By using async, you can use await inside the function to handle Promises in a cleaner,
 * more readable manner compared to chaining .then() calls.
 * The IIFE is useful for one-time operations where you don’t need to reuse the function.
 */
(async () => {
  const username = 'sagarpatel288';
  const repo = 'kotlinDSAWithIntellijIdea';

  /**
   * We need accessToken while using the GitHub API to avoid rate limit issues.
   * This is a standard practice to include accessToken in the header.
   * This is not a hard-coded token, so we are not exposing it anywhere, thus minimizing security risks.
   * Process is an object provided by Node.js to interact with the current running process.
   * It contains useful information about the environment in which the script is running.
   * env is a property of the process object, which is a collection of environment variables.
   * Environment variables are key-value pairs that are typically set outside the code,
   * like in the terminal or by a deployment system. They are used to store sensitive data, configurations, or tokens.
   * GITHUB_TOKEN is the name of an environment variable.
   * In GitHub Actions workflows, this token is often automatically created and set by GitHub.
   * It is used to authenticate API requests to GitHub, allowing the script to perform actions
   * such as creating, updating, or deleting repositories, managing issues, or making other GitHub API requests.
   */
  const accessToken = process.env.GITHUB_TOKEN;

  /**
   * We create an array to store names and corresponding urls of the files we fetch from a repository.
   * Later, we use this array to update a json file.
   * Variables declared with let are block-scoped, meaning their availability is limited to the block of code
   * (e.g., a function or a loop) in which they are defined.
   * let also allows reassignment, so you can change the value of kotlinFiles later.
   * This array can then be manipulated, filtered, sorted, or used for any other operation required by the script.
   */
  let kotlinFiles = [];

  /*
   * This is a function declaration in JavaScript.
   * The async keyword is used to declare an asynchronous function.
   * An async function always returns a Promise.
   * It allows you to use the await keyword inside the function,
   * which makes it easier to work with asynchronous code in a way that looks synchronous,
   * helping to avoid "callback hell."
   * Default value: path = '' means that the path parameter has a default value of an empty string.
   * If you call fetchKotlinFiles() without providing a path, it will default to an empty string.
   */
  async function fetchKotlinFiles(path = '') {
    try {
      // We need to pass header that includes a token while working with GitHub APIs smoothly.
      const headers = {
        Authorization: `Bearer ${accessToken}`
      };

      /*
       * `?t=${new Date().getTime()}`: This part adds a query parameter called t to the URL.
       * The query parameter t effectively ensures that the request URL is unique each time it is executed.
       * The purpose of this is to prevent caching and avoid 304 Not modified error.
       * If we don't use the `t` parameter, we need to implement a code that would use a cached version on getting 304.
       * It is recommended to use a cached version only.
       */
      const response = await fetch(`https://api.github.com/repos/${username}/${repo}/contents/${path}?t=${new Date().getTime()}`, { headers });
      if (!response.ok) {
        throw new Error(`Network response was not ok for path: ${path}`);
      }
      const data = await response.json();
      if (Array.isArray(data)) {
        for (const item of data) {
          if (item.type === 'dir') {
            await fetchKotlinFiles(item.path);
          } else if (item.type === 'file' && item.name.endsWith('.kt')) {
              // Fetch additional details for each file to get dates (createdDate and modifiedDate)
              const commitInfo = await fetch(`https://api.github.com/repos/${username}/${repo}/commits?path=${item.path}`, { headers });
                if (!commitInfo.ok) {
                  throw new Error(`Failed to fetch commit details for ${item.name}`);
                }
                const commits = await commitInfo.json();
                const createdDate = commits[commits.length - 1].commit.author.date;
                const modifiedDate = commits[0].commit.author.date;
                kotlinFiles.push({
                                name: item.name,
                                url: item.html_url,
                                createdDate: createdDate,
                                modifiedDate: modifiedDate,
                               });
          }
        }
      }
    } catch (error) {
      console.error(`Error fetching file list for path ${path}:`, error);
    }
  }

  /*
   * This line calls an asynchronous function named fetchKotlinFiles within a try-catch block to handle any errors.
   * The keyword `await` ensures that the script waits for the fetchKotlinFiles() function to complete
   * before moving to the next line.
   */
  try {
    await fetchKotlinFiles();
    fs.writeFileSync('files.json', JSON.stringify(kotlinFiles, null, 2));
    console.log('files.json has been saved with the latest Kotlin files.');
  } catch (error) {
    console.error('Error writing to files.json:', error);
  }
})();