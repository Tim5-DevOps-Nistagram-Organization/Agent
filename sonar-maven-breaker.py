import requests
import sys
import time
import argparse

# dobija se uvek poslednji izvestaj kad se pogodi url

URL = "https://sonarcloud.io/api/qualitygates/project_status"

parser = argparse.ArgumentParser()
parser.add_argument("--initialWait", type=int,nargs='?', default=15, const=15)
parser.add_argument("--projectKey", type=str,nargs='?', default="Proba1212Key", const='Proba1212Key')
parser.add_argument("--branch", type=str,nargs='?', default="main", const='main')
parser.add_argument("--testLogs", type=str,nargs='?', default="", const='')
args = parser.parse_args()


PROJECT_KEY = args.projectKey
PROJCT_BRANCH = args.branch
INITIAL_WAIT = args.initialWait
SERVER_LOGS = args.testLogs

TEST_FAILED = False


print("JA SAM SERVER LOGS \n: " + SERVER_LOGS)


if "ERROR" in SERVER_LOGS:
    print("========================>>> TESTS FAILED  <<<========================")
    sys.exit(1)


PARAMS = {'projectKey': PROJECT_KEY, "branch": PROJCT_BRANCH}

# initial sleep because need sometime to upload resuls on server
time.sleep(INITIAL_WAIT)
# see results
r = requests.get(url = URL, params = PARAMS)
status = r.json()['projectStatus']['status']
if status == "ERROR":
    print("========================>>> QUALITY GATE ERROR <<<========================")
    sys.exit(1)
else:
    print("========================>>> QUALITY GATE OKEJ <<<========================")
    sys.exit(0)
