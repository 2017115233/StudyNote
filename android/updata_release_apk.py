# -*- coding: utf-8 -*-
import os
import shutil


def main():
    #打印工作目录的路径
    #print(os.getcwd())

    #当前目录的文件夹名
    folder_name = os.path.basename(os.getcwd())
    project_name = "sys_" + folder_name

    target_dir = os.path.join("app", "build", "outputs", "apk", "release")
    apk_file = os.path.join(target_dir, "app-release-unsigned.apk")

    out_dir = os.path.join("doc", project_name)
    out_apk = os.path.join(out_dir, project_name + ".apk")

    if os.path.isdir(target_dir):
        if os.path.isfile(apk_file):
            print("find apk:")
            if os.path.isdir(out_dir):
                if os.path.exists(out_apk):
                    os.remove(out_apk)
                try:
                    shutil.copy2(apk_file, out_apk)
                    if os.path.exists(out_apk):
                        print("copy apk ok:")
                        shutil.copy2(out_apk, project_name + ".apk")
                    
                except Exception as e:
                    print("copy apk error")
            else:
                print("no out dir:"+out_dir)
        else:
            print("no apk:"+apk_file)
    else:
        print("no dir:"+target_dir)
 
 
 
 
 
 
 
 
 
if __name__ == "__main__":
    main()