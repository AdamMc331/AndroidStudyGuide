message("Thanks @#{github.pr_author}!")

dependencyReportsFile = "app/build/dependencyUpdates/report.txt"
dependencyUpdatesHeader = "The following dependencies have later milestone versions:"

hasUpdates = File.readlines(dependencyReportsFile).grep(/#{dependencyUpdatesHeader}/).any?

if hasUpdates
    file = File.open(dependencyReportsFile, "rb").read
    index = file.index(dependencyUpdatesHeaader)
    updates = file.slice(index..-1)
    warn(updates)
end