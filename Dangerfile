message("Thanks @#{github.pr_author}!")

dependencyReportsFile = "app/build/dependencyUpdates/report.txt"
dependencyUpdatesHeader = "The following dependencies have later milestone versions:"

hasUpdates = File.readlines(dependencyReportsFile).grep(/#{dependencyUpdatesHeader}/).any?

if hasUpdates
    file = File.open(dependencyReportsFile, "rb").read
    index = file.index(dependencyUpdatesHeader)
    updates = file.slice(index..-1)
    warn(updates)
end

failure("Please add labels to this PR.") if github.pr_labels.empty?

checkstyle_format.base_path = Dir.pwd
checkstyle_format.report("app/build/reports/detekt/detekt.xml")

Dir.glob("app/src/main/res/layout") do |file|
    hasMaterialBottomView = File.readlines(file).grep(/"com.google.android.material.bottomnavigation.BottomNavigationView"/).any?

    if hasMaterialBottomView
        warn("Found reference of Material BottomNavigationView in #{filename}.")
    end
end