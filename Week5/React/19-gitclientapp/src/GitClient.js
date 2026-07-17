import axios from 'axios';

// Exercise 19: module that calls the GitHub API to fetch repositories
// for a given user and returns the repository names.
class GitClient {
  getRepositories(user) {
    return axios.get(`https://api.github.com/users/${user}/repos`).then((response) => {
      return response.data.map((repo) => repo.name);
    });
  }
}

export default GitClient;
