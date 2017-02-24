#!/bin/bash
 
basename=$1 #引数からファイル名を受取る
filename=${basename%.*} #拡張子を取った名前

if [ -a ${filename}.cpp ]; then

		clang++ -o ${filename} ${filename}.cpp

    if [ -a ${filename} ]; then

        ./${filename}

    else
        echo "** ${filename} do not made **"
    fi
else
    echo "** ${filename}.cpp does not exist **"
fi