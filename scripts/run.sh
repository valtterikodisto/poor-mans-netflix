file="download.sh"

if [ -f $file ] ; then
	rm $file
fi

java -jar ../target/PoorMansNetflix-1.0-SNAPSHOT-jar-with-dependencies.jar
chmod +x download.sh
./download.sh
