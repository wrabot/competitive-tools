My tools for competitive programming

To use them as source dependency

In settings.gradle.kts, add

```
sourceControl {
    gitRepository(java.net.URI.create("https://github.com/wrabot/competitive-tools.git")) {
        producesModule("wrabot.competitive:CompetitiveTools")
    }
}
```
In build.gradle.kts, add

```
implementation("wrabot.competitive:CompetitiveTools:0.45")
```
