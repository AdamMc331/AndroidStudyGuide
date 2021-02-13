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

checkstyle_format.base_path = Dir.pwd
checkstyle_format.report("app/build/reports/detekt/detekt.xml")

materialBottomViewClass = "com.google.android.material.bottomnavigation.BottomNavigationView"

Dir.foreach("app/src/main/res/layout") do |filename|
  next if filename == '.' or filename == '..'

  completeFileName = "app/src/main/res/layout/#{filename}"

  hasMaterialBottomView = File.readlines(completeFileName).grep(/#{materialBottomViewClass}/).any?

  if hasMaterialBottomView
      warn("Found reference of Material BottomNavigationView in #{filename}.")
  end

end
