import axios from 'axios';
import GitClient from './GitClient';

// Mock the axios module so no real network call is made.
jest.mock('axios');

describe('Git Client Tests', () => {
  test('should return repository names for techiesyed', async () => {
    const dummyData = {
      data: [{ name: 'repo-one' }, { name: 'repo-two' }, { name: 'repo-three' }],
    };

    // Configure the mock to resolve with the dummy data.
    axios.get.mockResolvedValue(dummyData);

    const client = new GitClient();
    const repos = await client.getRepositories('techiesyed');

    // Verify we got the mocked data instead of a real API response.
    expect(repos).toEqual(['repo-one', 'repo-two', 'repo-three']);
    expect(axios.get).toHaveBeenCalledWith('https://api.github.com/users/techiesyed/repos');
  });
});
