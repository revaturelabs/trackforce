# script for quickly committing changes
# Author : Michael Warren
# Parameters: 
# • message to commit changes with 
# • (optional) the name of the branch to commit to

TO_BRANCH=$(git branch | grep \* | cut -c 3-) # the current branch of the user
FROM_BRANCH=$(git branch | grep \* | cut -c 3-) # sets pull branch from user's current branch if none is specified

if [[ $1 == "--help" || $2 == "--help" || $3  == "--help" ]]; then
	echo "This script quickly commits changes to your GitHub repo. By default, this script pulls FROM your repo."
	echo ""
	echo "Parameters : "
	echo "• message to commit changes with. MUST be in quotes iff it's more than one word!"
	echo "• the name of the branch to pull from"
	echo "• (optional) the name of the branch to push to"
	echo ""
	echo "If second argument is not specified, this will commit changes to TO_BRANCH."
	echo ""
	echo "Setup"
	echo "If you want this script to pull from a branch other than your own, by default, you should change FROM_BRANCH"
else

	# pull changes from current branch
	[[ ! -z "$2" ]] && git pull origin $2 || git pull origin $FROM_BRANCH
	# add everything
	git add .
	# remove this file from checkout, whatever its name
	git rm `basename "$0"`
	# if there was a first parameter passed into this script, use it for the message. Else, just commit
	[[ ! -z "$1" ]] && git commit -m "$1" || git commit
	# if there was a second parameter passed into this script, push to TO_BRANCH. Else, push to that second parameter
	[[ ! -z "$3" ]] && git push origin $3 || git push origin $TO_BRANCH
fi
