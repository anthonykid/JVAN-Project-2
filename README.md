# JVAN-Project-2
this is our android project number 2, we make a small ecommerce app with 3 roles (admin, staff, and user), its a small app that the 3 of us develop, the app was named tokobay, we are using local storage for now, because we haven't hosted the db, for now we are using phpmyadmin for the db.
I would explain in Indonesian for the reviewer.
Hal yang diperlukan dalam menjalankan aplikasi tersebut :
- Android Studio
- XAMPP

Langkah yang harus dilakukan adalah :
1. Menginstall Android Studio terlebih dahulu dan menjalankan nya, kemudian membuat clone dari repository tersebut.
2. Menginstall XAMPP dan menjalankan Apache dengan MySQL, setelah itu download jvan2.rar yang terdapat pada repository tersebut.
3. Extract Repository, dan kita akan melakukan 2 hal, yang pertama adalah memindahkan semua file PHP dan folder gambar ke dalam htdocs, pada lokasi penginstallan XAMPP, adanya kemungkinan pada Local Disk C > xampp > htdocs > project2
4. Selanjutnya file yang memiliki ekstensi .SQL kita akan import pada database xampp kita, buka lah terlebih dahulu panel phpmyadmin.
5. Create db baru dengan menggunakan nama jvan2 lalu klik impor, setelah itu
6. Impor lah 2 db yang menggunakan ekstensi file .SQL yang terdapat pada jvan2.rar
7. Buka lah kembali Android Studio dan bukalah bagian ApiClient.java & network_security_config.xml (disini terdapat 2 IP yang diminta. disini kita harus memberikan IP yang sama dengan IP yang kita gunakan (akan berbeda beda pada tiap User)
8. Gantilah IP pada ApiClient.java & network_security_config.xml ke IP anda sendiri
9. Jalankan Project pada emulator yang tersedia pada Android Studio. Kami menyarankan menggunakan Emulator dengan API 30
10. Jika anda sudah menjalankan langkah diatas dengan benar, anda dapat lanjut signin, atau melakukan proses sign up dulu untuk menjadi user
11. Pada saat sign in anda akan ditentukan apakah anda adalah admin, staff, ataupun user dan akan berpindah ke layar yang seharusnya dituju dari tiap role
