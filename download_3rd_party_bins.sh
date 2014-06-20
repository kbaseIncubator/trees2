#!/bin/bash
cd ./data
mkdir temp
cd ./temp
unamestr=`uname`
if [[ "$unamestr" == 'Linux' ]]; then
	echo "OS architecture: linux"
	########### Blast #############
	if [ ! -f ../bin/rpsblast.linux ]; then
		echo "Downloading blast..."
		curl -o blast.tar.gz 'ftp://ftp.ncbi.nlm.nih.gov/blast/executables/blast+/2.2.29/ncbi-blast-2.2.29+-x64-linux.tar.gz'
		tar -zxvf blast.tar.gz ncbi-blast-2.2.29+/bin/makeprofiledb ncbi-blast-2.2.29+/bin/rpsblast
		mv ./ncbi-blast-2.2.29+/bin/makeprofiledb ../bin/makeprofiledb.linux
		mv ./ncbi-blast-2.2.29+/bin/rpsblast ../bin/rpsblast.linux
		rmdir ./ncbi-blast-2.2.29+/bin
		rmdir ./ncbi-blast-2.2.29+
		rm ./blast.tar.gz
	fi
	########### ClustalW ###########
	if [ ! -f ../bin/clustalw2.linux ]; then
		echo "Downloading clustalw..."
		curl -o clustalw.tar.gz 'http://www.clustal.org/download/2.1/clustalw-2.1-linux-x86_64-libcppstatic.tar.gz'
		tar -zxvf clustalw.tar.gz clustalw-2.1-linux-x86_64-libcppstatic/clustalw2
		mv ./clustalw-2.1-linux-x86_64-libcppstatic/clustalw2 ../bin/clustalw2.linux
		rmdir ./clustalw-2.1-linux-x86_64-libcppstatic
	fi
	########### Muscle #############
	if [ ! -f ../bin/muscle.linux ]; then
		echo "Downloading muscle..."
		curl -o muscle.tar.gz 'http://www.drive5.com/muscle/downloads3.8.31/muscle3.8.31_i86linux64.tar.gz'
		tar -zxvf muscle.tar.gz muscle3.8.31_i86linux64
		mv ./muscle3.8.31_i86linux64 ../bin/muscle.linux
		rm ./muscle.tar.gz
	fi
elif [[ "$unamestr" == 'Darwin' ]]; then
	echo "OS architecture: mac os x"
	########### Blast #############
	if [ ! -f ../bin/rpsblast.macosx ]; then
		echo "Downloading blast..."
		curl -o blast.tar.gz 'ftp://ftp.ncbi.nlm.nih.gov/blast/executables/blast+/2.2.29/ncbi-blast-2.2.29+-universal-macosx.tar.gz'
		tar -zxvf blast.tar.gz ncbi-blast-2.2.29+/bin/makeprofiledb ncbi-blast-2.2.29+/bin/rpsblast
		mv ./ncbi-blast-2.2.29+/bin/makeprofiledb ../bin/makeprofiledb.macosx
		mv ./ncbi-blast-2.2.29+/bin/rpsblast ../bin/rpsblast.macosx
		rmdir ./ncbi-blast-2.2.29+/bin
		rmdir ./ncbi-blast-2.2.29+
		rm ./blast.tar.gz
	fi
	########### ClustalW ###########
	if [ ! -f ../bin/clustalw2.macosx ]; then
		echo "Downloading clustalw..."
		curl -o clustalw-2.1-macosx.dmg 'http://www.clustal.org/download/2.1/clustalw-2.1-macosx.dmg'
		hdiutil attach ./clustalw-2.1-macosx.dmg -mountroot ./
		cp ./clustalw-2.1-macosx/clustalw-2.1-macosx/clustalw2 ../bin/clustalw2.macosx
		hdiutil detach ./clustalw-2.1-macosx
		rm ./clustalw-2.1-macosx.dmg
	fi
	########### Muscle #############
	if [ ! -f ../bin/muscle.macosx ]; then
		echo "Downloading muscle..."
		curl -o muscle.tar.gz 'http://www.drive5.com/muscle/downloads3.8.31/muscle3.8.31_i86darwin64.tar.gz'
		tar -zxvf muscle.tar.gz muscle3.8.31_i86darwin64
		mv ./muscle3.8.31_i86darwin64 ../bin/muscle.macosx
		rm ./muscle.tar.gz
	fi
else
	echo "Unknown OS architecture: $unamestr"
fi
