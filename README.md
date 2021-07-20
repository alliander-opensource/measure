# Dynamo Kotlin Template
This repository is a template for a [Kotlin][kotlin] based project as used by the Dynamo team.

## How To Use
This template can be used in the following way. When creating a creating a repository on GitHub one can choose to base it of a template. If you want to base your project from the `dynamo-kotlin-template`, you should choose it from the menu. It will create a repository that mimics this exact repository. For more information see [GitHub documentation][github:create-from-template].

One should then modify the repository to reflect your project. One has to know the name of the project. We will use the place holder `project_name` in certain places.

One can use the following checklist to make sure the all steps are done in order.

* [ ] Rename the `project` directory to `project_name`.
* [ ] Rename `project` in `settings.gradle.kts` to `project_name`.
* [ ] Personalize `CONTRIBUTING.md`.
* [ ] When used, personalize `.github` directory.
* [ ] Remove or rename the example code.
* [ ] Describe the project in the `README`.
* [ ] Tweak the settings of the project.
* [ ] Remove the *How To Use* section in the `README`.
 
## Development
This project uses [Gradle][gradle] as a build tool. To see which tasks are available run

```
./gradlew tasks
```
[github:create-from-template]: https://docs.github.com/en/github/creating-cloning-and-archiving-repositories/creating-a-repository-on-github/creating-a-repository-from-a-template
[kotlin]: https://kotlinlang.org/
[gradle]: https://gradle.org/
