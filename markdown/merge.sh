#!/bin/bash
project_path=./
if [ $# == 1 ]
then
	project_path="$1"
else 
	project_path=./
fi
if [ ! -d $project_path ]
	then
	echo $project_path is not dir
	return
fi
echo $project_path

# project_path=/Users/wiesen/AndroidProject/snake-android
apk_output=$project_path/app/build/outputs/apk
sign_path=$project_path/app/wespy_snake_sign.jks

branch_position=0

branch_names=(
# 这里注释不要的渠道
  feature-adapter
  feature_ali_support
  feature_chest_animator
  feature_clean
  feature_deeplink
  feature_dexload
  feature_fullscreen_no_nava
  feature_gif
  feature_glide
  feature_lottie
  feature_migu
  feature_multi_dimen
  feature_percent_layout
  feature_principle
  feature_scan_qrcode
  feature_skinconfig
  feature_upload_base
  feature_webview_input
  feature_widgets
  feature_widgets2
  master
)

branch_map=(
  feature-adapter
  master
  feature_ali_support
  master
  feature_chest_animator
  master
  feature_clean
  master
  feature_deeplink
  master
  feature_dexload
  master
  feature_fullscreen_no_nava
  master
  feature_gif
  master
  feature_glide
  master
  feature_lottie
  master
  feature_migu
  master
  feature_multi_dimen
  master
  feature_percent_layout
  master
  feature_principle
  master
  feature_scan_qrcode
  master
  feature_skinconfig
  master
  feature_upload_base
  master
  feature_webview_input
  master
  feature_widgets
  master
  feature_widgets2
  master
	)

function exit_error()
{
	echo $1
	if [ ! $1 -eq 0 ];
	then 
		echo "命令出错"
		exit -1
	fi
}

function find_merge_position()
{
	local branch=$1
	local size=${#branch_map[@]}
	local position=0
	while [ $size -gt $position ]
	do
		# echo $position
		# echo ${branch_map[${position}]}
		if [ $branch = ${branch_map[${position}]} ]
		then
			branch_position=`expr $position + 1`
			return 1
		fi
   		position=`expr $position + 1`
	done
}

function merge()
{
	local branch=$1
	find_merge_position $1
	# echo $brancn
	echo "merge  "  $branch
	git checkout $branch
	git pull
	git merge ${branch_map[$branch_position]} --no-edit
	exit_error $?
}

function push()
{
	cd $project_path
	pwd
	for branchName in ${branch_names[@]} 
	do
		git checkout $branchName
		git push
	done
}

function main()
{
	cd $project_path
	pwd
	for branchName in ${branch_names[@]} 
	do
		merge $branchName
	done
}

main

push
