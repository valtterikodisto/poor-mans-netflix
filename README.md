# Poor Man's Netflix
Downloads torrents based on name, season and episode. Searches for TV series from OMDb API and uses the IMDb ID
to download a torrent from eztv using their API (beta mode). The torrent is downloaded using transmission-cli.

## Getting started
Open a terminal and type:
```
git clone https://github.com/valtterikodisto/poor-mans-netflix.git
```

### Prerequisites
First you will need Java and Maven. If you dont have them installed, open a terminal and type:
```
sudo apt-get update
```
For Java
```
sudo apt-get install default-jdk
```
For Maven
```
sudo apt install maven
```
Finally you will need to install transmission-cli and psmisc. I have created a script to install them but
you can also install them manually.
```bash
cd poor-mans-netflix/scripts
chmod +x install.sh && ./install.sh
```

### Get it up and running
To access OMDb API you will need an API key which you can get from here: http://www.omdbapi.com/apikey.aspx
The free version has 1000 daily limit and I'm planning to use a database to minimize the requests.
Once you get the API key navigate to poor-mans-netflix and type:
```bash
echo OMDB_APIKEY=yourapikey > .env
```
Then we need to just add some Maven magic:
```bash
mvn package
```
After that navigate to the 'scripts' folder:
```bash
cd ./scripts
```
Now to get it running just type:
```bash
./run.sh
```
After the download finished, the torrent can be found in the PoorMansNetflixVideos which is located in your home folder:
```bash
cd $HOME/PoorMansNetflixVideos
```

## Other
### Problems
Since eztv API in beta mode the requests sometimes fail. Just try again later and it should start working again.

### :no_entry_sign: Disclaimer :no_entry_sign:
**The code is for educational purposes only**
