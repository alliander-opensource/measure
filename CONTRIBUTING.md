# Contributing to Project
We'd love to accept your patches and contributions to this project. There are just a few small guidelines you need to follow.

## Ways of contributing

Contribution does not necessarily mean committing code to the repository. 
We recognize different levels of contributions as shown below in increasing order of dedication:

1. Test and use the libray. Give feedback on the user experience or suggest new features.
3. Report bugs.
4. Improve the documentation
5. Contributing to the Kotlin code

## Community Guidelines
This project follows the following [Code of Conduct][code-of-conduct].

## What to do?
Before spending your time and effort, consult the [issues page][project:issues] and [discussion page][project:discussion] and find things to work on. Dont' be afraid to make yourself and your plans known.

## Contributing to documentation
Documentation is a very important part of a project. If people don't know how to use your tool, it does not matter how good it is, it will not be used.

Contribute to the documentation on the [documentation page][project:documentation].

[code-of-conduct]: https://github.com/Alliander/project/blob/master/CODE_OF_CONDUCT.md
[project:issues]: https://github.com/Alliander/project/issues
[project:discussion]: https://github.com/Alliander/project/discussions
[project:documentation]: https://github.com/Alliander/project/wiki

## Signing the Developer Certificate of Origin (DCO)

This project utilize a Developer Certificate of Origin (DCO) to ensure that 
each commit was written by the author or that the author has the appropriate rights 
necessary to contribute the change. 
Specifically, we utilize [Developer Certificate of Origin, Version 1.1](http://developercertificate.org/), 
which is the same mechanism that the Linux® Kernel and many other communities use to manage code contributions. 
The DCO is considered one of the simplest tools for sign-offs from contributors as the representations are 
meant to be easy to read and indicating signoff is done as a part of the commit message.

This means that each commit must include a DCO which looks like this:

`Signed-off-by: Joe Smith <joe.smith@email.com>`

The project requires that the name used is your real name and the e-mail used is your real e-mail. 
Neither anonymous contributors nor those utilizing pseudonyms will be accepted.

There are other great tools out there to manage DCO signoffs for developers to make it much easier to do signoffs:
* Git makes it easy to add this line to your commit messages. Make sure the `user.name` and `user.email` are set in your git configs. Use `-s` or `--signoff` to add the Signed-off-by line to the end of the commit message.
* [GitHub UI integrations]( https://github.com/scottrigby/dco-gh-ui ) for adding the signoff automatically to commits made with the GitHub browser UI
* Additionally, it is possible to use shell scripting to automatically apply the sign-off. For an example for bash to be put into a .bashrc file, see [here](https://wiki.lfenergy.org/display/HOME/Contribution+and+Compliance+Guidelines+for+LF+Energy+Foundation+hosted+projects). 
* Alternatively, you can add `prepare-commit-msg hook` in .git/hooks directory. For an example, see [here](https://github.com/Samsung/ONE-vscode/wiki/ONE-vscode-Developer's-Certificate-of-Origin).

## Code reviews

All patches and contributions, including patches and contributions by project members, require review by one of the maintainers of the project. We
use GitHub pull requests for this purpose. Consult the pull request process below and the
[GitHub Help](https://help.github.com/articles/about-pull-requests/) for more
information on using pull requests

## Pull Request Process
Contributions should be submitted as Github pull requests. See [Creating a pull request](https://docs.github.com/en/github/collaborating-with-issues-and-pull-requests/creating-a-pull-request) if you're unfamiliar with this concept.

The process for a code change and pull request you should follow:

1. Create a topic branch in your local repository, following the naming format
"feature-###" or "fix-###". For more information see the Git branching guideline.
1. Make changes, compile, and test thoroughly. Ensure any install or build dependencies are removed before the end of the layer when doing a build. Code style should match existing style and conventions, and changes should be focused on the topic the pull request will be addressed. For more information see the style guide.
1. Push commits to your fork.
1. Create a Github pull request from your topic branch.
1. Pull requests will be reviewed by one of the maintainers who may discuss, offer constructive feedback, request changes, or approve
the work. For more information see the Code review guideline.
1. Upon receiving the sign-off of one of the maintainers you may merge your changes, or if you
   do not have permission to do that, you may request a maintainer to merge it for you.
