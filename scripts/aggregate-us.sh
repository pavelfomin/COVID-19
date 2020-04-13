cmd="java -jar $(ls target/covid19-*.jar)"
filter="US_Minnesota US_Texas US_Wisconsin US_Oklahoma US_Colorado"
inputDir=csse_covid_19_data/csse_covid_19_daily_reports
output=aggregated/covid19-us.csv

#$cmd $inputDir/03-23-2020.csv State None $filter > $output
#$cmd $inputDir/03-24-2020.csv State None $filter | tail -n+2 >> $output
#$cmd $inputDir/03-25-2020.csv State None $filter | tail -n+2 >> $output
#$cmd $inputDir/03-26-2020.csv State None $filter | tail -n+2 >> $output
#$cmd $inputDir/03-27-2020.csv State None $filter | tail -n+2 >> $output
#$cmd $inputDir/03-28-2020.csv State None $filter | tail -n+2 >> $output
#$cmd $inputDir/03-29-2020.csv State None $filter | tail -n+2 >> $output
#$cmd $inputDir/03-30-2020.csv State None $filter | tail -n+2 >> $output
#$cmd $inputDir/03-31-2020.csv State None $filter | tail -n+2 >> $output
#$cmd $inputDir/04-01-2020.csv State None $filter | tail -n+2 >> $output
#$cmd $inputDir/04-02-2020.csv State None $filter | tail -n+2 >> $output
#$cmd $inputDir/04-03-2020.csv State None $filter | tail -n+2 >> $output
#$cmd $inputDir/04-04-2020.csv State None $filter | tail -n+2 >> $output
#$cmd $inputDir/04-05-2020.csv State None $filter | tail -n+2 >> $output
#$cmd $inputDir/04-06-2020.csv State None $filter | tail -n+2 >> $output
#$cmd $inputDir/04-07-2020.csv State None $filter | tail -n+2 >> $output
#$cmd $inputDir/04-08-2020.csv State None $filter | tail -n+2 >> $output
#$cmd $inputDir/04-09-2020.csv State None $filter | tail -n+2 >> $output
#$cmd $inputDir/04-10-2020.csv State None $filter | tail -n+2 >> $output
#$cmd $inputDir/04-11-2020.csv State None $filter | tail -n+2 >> $output
$cmd $inputDir/04-12-2020.csv State None $filter | tail -n+2 >> $output