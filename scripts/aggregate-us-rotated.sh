cmd="java -jar $(ls target/covid19-*.jar)"
filter="US_Minnesota US_Texas US_Wisconsin US_Oklahoma US_Colorado"
inputDir=csse_covid_19_data/csse_covid_19_daily_reports
output=aggregated/covid19-us-rotated.csv

$cmd $inputDir/03-23-2020.csv State Rotate $filter > $output
$cmd $inputDir/03-24-2020.csv State Rotate $filter | tail -n+2 >> $output
$cmd $inputDir/03-25-2020.csv State Rotate $filter | tail -n+2 >> $output
$cmd $inputDir/03-26-2020.csv State Rotate $filter | tail -n+2 >> $output
$cmd $inputDir/03-27-2020.csv State Rotate $filter | tail -n+2 >> $output
$cmd $inputDir/03-28-2020.csv State Rotate $filter | tail -n+2 >> $output
$cmd $inputDir/03-29-2020.csv State Rotate $filter | tail -n+2 >> $output
$cmd $inputDir/03-30-2020.csv State Rotate $filter | tail -n+2 >> $output
$cmd $inputDir/03-31-2020.csv State Rotate $filter | tail -n+2 >> $output
$cmd $inputDir/04-01-2020.csv State Rotate $filter | tail -n+2 >> $output
$cmd $inputDir/04-02-2020.csv State Rotate $filter | tail -n+2 >> $output
$cmd $inputDir/04-03-2020.csv State Rotate $filter | tail -n+2 >> $output
$cmd $inputDir/04-04-2020.csv State Rotate $filter | tail -n+2 >> $output
$cmd $inputDir/04-05-2020.csv State Rotate $filter | tail -n+2 >> $output
$cmd $inputDir/04-06-2020.csv State Rotate $filter | tail -n+2 >> $output
$cmd $inputDir/04-07-2020.csv State Rotate $filter | tail -n+2 >> $output
$cmd $inputDir/04-08-2020.csv State Rotate $filter | tail -n+2 >> $output
$cmd $inputDir/04-09-2020.csv State Rotate $filter | tail -n+2 >> $output
$cmd $inputDir/04-10-2020.csv State Rotate $filter | tail -n+2 >> $output
$cmd $inputDir/04-11-2020.csv State Rotate $filter | tail -n+2 >> $output
$cmd $inputDir/04-12-2020.csv State Rotate $filter | tail -n+2 >> $output
$cmd $inputDir/04-13-2020.csv State Rotate $filter | tail -n+2 >> $output
$cmd $inputDir/04-14-2020.csv State Rotate $filter | tail -n+2 >> $output
$cmd $inputDir/04-15-2020.csv State Rotate $filter | tail -n+2 >> $output
$cmd $inputDir/04-16-2020.csv State Rotate $filter | tail -n+2 >> $output
$cmd $inputDir/04-17-2020.csv State Rotate $filter | tail -n+2 >> $output
$cmd $inputDir/04-18-2020.csv State Rotate $filter | tail -n+2 >> $output
$cmd $inputDir/04-19-2020.csv State Rotate $filter | tail -n+2 >> $output
$cmd $inputDir/04-20-2020.csv State Rotate $filter | tail -n+2 >> $output
$cmd $inputDir/04-21-2020.csv State Rotate $filter | tail -n+2 >> $output
$cmd $inputDir/04-22-2020.csv State Rotate $filter | tail -n+2 >> $output
$cmd $inputDir/04-23-2020.csv State Rotate $filter | tail -n+2 >> $output
$cmd $inputDir/04-24-2020.csv State Rotate $filter | tail -n+2 >> $output
$cmd $inputDir/04-25-2020.csv State Rotate $filter | tail -n+2 >> $output
$cmd $inputDir/04-26-2020.csv State Rotate $filter | tail -n+2 >> $output
$cmd $inputDir/04-27-2020.csv State Rotate $filter | tail -n+2 >> $output
$cmd $inputDir/04-28-2020.csv State Rotate $filter | tail -n+2 >> $output
$cmd $inputDir/04-29-2020.csv State Rotate $filter | tail -n+2 >> $output
$cmd $inputDir/04-30-2020.csv State Rotate $filter | tail -n+2 >> $output
$cmd $inputDir/05-01-2020.csv State Rotate $filter | tail -n+2 >> $output
$cmd $inputDir/05-02-2020.csv State Rotate $filter | tail -n+2 >> $output
$cmd $inputDir/05-03-2020.csv State Rotate $filter | tail -n+2 >> $output
$cmd $inputDir/05-04-2020.csv State Rotate $filter | tail -n+2 >> $output
$cmd $inputDir/05-05-2020.csv State Rotate $filter | tail -n+2 >> $output
$cmd $inputDir/05-06-2020.csv State Rotate $filter | tail -n+2 >> $output
$cmd $inputDir/05-07-2020.csv State Rotate $filter | tail -n+2 >> $output
$cmd $inputDir/05-08-2020.csv State Rotate $filter | tail -n+2 >> $output
$cmd $inputDir/05-09-2020.csv State Rotate $filter | tail -n+2 >> $output
$cmd $inputDir/05-10-2020.csv State Rotate $filter | tail -n+2 >> $output
$cmd $inputDir/05-11-2020.csv State Rotate $filter | tail -n+2 >> $output
$cmd $inputDir/05-12-2020.csv State Rotate $filter | tail -n+2 >> $output
$cmd $inputDir/05-13-2020.csv State Rotate $filter | tail -n+2 >> $output