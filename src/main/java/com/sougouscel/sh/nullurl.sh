#!/bin/sh


#count=0
#strurls=`cat download.txt`
#strurls=`cat noget.txt`
#strurls=`cat nourl.txt`

#echo ${#strurls[*]}
#for i in $strurls

#do
  
  
#  ((count++)); 
  #echo $count
  #echo $i >> done.txt
  #str=${i// /-}
#  echo $i
 # filename=`echo $i|awk -F 'name=' '{print $2}'`
  #wget -O $filename$count.scel $i
  #echo $filename
#done


count=0

while read  b
 do
   ((count++))

 # echo $b
 #filename=`echo "$b"|awk '{print $2}'`
 filename=`echo "$b"|sed s/[[:space:]]//g |awk -F 'name=' '{print $2}'`
 #echo $filename
  wget -O $filename$count.scel "$b" 
 



done<  noget.txt
