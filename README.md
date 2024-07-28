
# GitHub Data Receiver


- [@nikodemgodek](https://www.github.com/nikodemgodek)
### Installation

Download project to your local directory

```bash
  git clone https://github.com/nikodemgodek/github-data-receiver.git
```

Open project in your IDE, find AtiperaApplication and run. Once all dependencies loaded and built application will start automatically.
    
### Configuration

Before using you need to provide necessary values like GITHUB_URL_API and YOUR_GITHUB_API_TOKEN.

```bash
github.api.url=GITHUB_URL_API
github.token=YOUR_GITHUB_API_TOKEN
```

### Endpoints

 http://<FQDN>/api/v1/repos/{username} | GET method

200:

username=nikodemgodek
 ```
 [
    {
        "repositoryName": "chatter",
        "ownerLogin": "nikodemgodek",
        "branches": [
            {
                "branchName": "master",
                "lastCommitSha": "7458bc0d001e086e1366ad4ad7012ad5d208cffd"
            }
        ]
    },
 ]

 ```


404:

username=invalidusername
 ```
 {
    "status": 404,
    "message" : "User not found"
 }

 ```
