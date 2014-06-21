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
	########### T-coffee ############
	if [ ! -f ../bin/tcoffee.linux ]; then
		echo "Downloading t-coffee..."
		curl -o tcoffee.tar.gz 'http://www.tcoffee.org/Packages/Stable/Version_10.00.r1613/T-COFFEE_distribution_Version_10.00.r1613.tar.gz'
		tar -zxvf tcoffee.tar.gz
		cd ./T-COFFEE_distribution_Version_10.00.r1613/t_coffee_source
		make
		mv ./t_coffee ../../../bin/tcoffee.linux
		cd ../..
		rm -r ./T-COFFEE_distribution_Version_10.00.r1613
		rm ./tcoffee.tar.gz
	fi
	########### ProbCons ############
	if [ ! -f ../bin/probcons.linux ]; then
		echo "Downloading probcons..."
		curl -o probcons.tar.gz 'http://probcons.stanford.edu/probcons_v1_12.tar.gz'
		tar -zxvf probcons.tar.gz
		cd ./probcons
		make
		mv ./probcons ../../bin/probcons.linux
		cd ..
		rm -r ./probcons
		rm probcons.tar.gz
	fi
	########### Mafft ##############
	if [ ! -f ../bin/mafft.linux ]; then
		echo "Downloading mafft..."
		curl -o mafft.tgz 'http://mafft.cbrc.jp/alignment/software/mafft-7.157-linux.tgz'
		tar -zxvf mafft.tgz
		mv ./mafft-linux64/mafftdir/bin/mafft ../bin/mafft.linux
		mv ./mafft-linux64/mafftdir/libexec ../bin/mafftlib.linux
		rm -r ./mafft-linux32
		rm -r ./mafft-linux64
		rm mafft.tgz
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
	########### T-coffee ############
	if [ ! -f ../bin/tcoffee.macosx ]; then
		echo "Downloading t-coffee..."
		curl -o tcoffee.tar.gz 'http://www.tcoffee.org/Packages/Stable/Version_10.00.r1613/T-COFFEE_distribution_Version_10.00.r1613.tar.gz'
		tar -zxvf tcoffee.tar.gz
		cd ./T-COFFEE_distribution_Version_10.00.r1613/t_coffee_source
		make
		mv ./t_coffee ../../../bin/tcoffee.macosx
		cd ../..
		rm -r ./T-COFFEE_distribution_Version_10.00.r1613
		rm ./tcoffee.tar.gz
	fi
	########### ProbCons ############
	if [ ! -f ../bin/probcons.macosx ]; then
		echo "Downloading probcons..."
		curl -o probcons.tar.gz 'http://probcons.stanford.edu/probcons_v1_12.tar.gz'
		tar -zxvf probcons.tar.gz
		cd ./probcons
		make
		mv ./probcons ../../bin/probcons.macosx
		cd ..
		rm -r ./probcons
		rm probcons.tar.gz
	fi
	########### Mafft ##############
	if [ ! -f ../bin/mafft.macosx ]; then
		echo "Downloading mafft..."
		curl -o mafft.zip 'http://mafft.cbrc.jp/alignment/software/mafft-7.157-mac.zip'
		unzip ./mafft.zip
		mv ./mafft-mac/mafftdir/bin/mafft ../bin/mafft.macosx
		mv ./mafft-mac/mafftdir/libexec ../bin/mafftlib.macosx
		rm -r ./mafft-mac
		rm mafft.zip
	fi
else
	echo "Unknown OS architecture: $unamestr"
fi
