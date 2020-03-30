cmd="java -jar target/covid19-0.0.1.jar"
inputDir=csse_covid_19_data/csse_covid_19_daily_reports
output=aggregated/covid19.csv

$cmd $inputDir/03-23-2020.csv > $output
$cmd $inputDir/03-24-2020.csv | tail -n+2 >> $output
$cmd $inputDir/03-25-2020.csv | tail -n+2 >> $output
$cmd $inputDir/03-26-2020.csv | tail -n+2 >> $output
$cmd $inputDir/03-27-2020.csv | tail -n+2 >> $output
$cmd $inputDir/03-28-2020.csv | tail -n+2 >> $output
$cmd $inputDir/03-29-2020.csv | tail -n+2 >> $output