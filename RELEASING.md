# Releasing procedure

After adding new features or bugfixes you might want to release the changes so that they can be used. To do this, we
follow a number of steps to make sure that every release is of a high quality.

1. Every PR to main the CHANGELOG.md is updated with the changes, this will be reviewed as well.
2. Before starting the actual release we must make sure that the version number is correct in the CHANGELOG.md. We 
   follow [semantic versioning](https://semver.org/).
3. Merge the last changes to main and create a new tag on main. The tag should be the version number without a 'v'. E.g.
   `1.3.5`.
4. A github action will be triggered, the release is build, signed and then pushed to maven central.
5. Maven central will validate the release. If the release passes, it can be published manually. For this someone has to
   login in maven central and push the "publish" button.
6. The release in github will be automatically created including the release notes.
7. After everything is done, it is nice to add a new header in the CHANGELOG.md with the next version number. Also 
   update the version number in the build.gradle.kts. (Note that the version that is used will be taken from the tagname
   NOT the version in the build.gradle.kts.)