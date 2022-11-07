# dempin.android_base_core

# imlementation


allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
 
 

dependencies {
	        implementation 'com.github.canvas-technology:dempin.android_base_core:VersionName'
	}
 

########


* Canvas altında yazılacak tüm android projeleri için bu kütüphane baz alınması için yazılmıştır.

* Base * 

 -> Base klasörü altında bulunan  BaseActiviy, BaseFragment, dialog ve adapter sınıfları proje genelinde farklı farklı yapılar kullanılmasının
önüne geçilmek için yazılmıştır. Ayrıca Mvvm mimarisi ve viewDataBinding kalıplarının uygun performans ve mimari ile kullanmasını sağlamak
amaç edinilimiştir.

* Extension * 

-> Objecler için gerekli ek özelliklerin sade ve anlaşılır bir şekilde kullanılması amaç edinerek yazılmıştır. ApiRespone Classı ile dempin 
api servislerinin genel response'una uygun olacak şekilde ayarlanıp proje içinde bu responseların doğru handle edilme içindir. String, boolean, double vb.
classlar içinde yardımcı fonksiyonları içirmektedir, bunlara ek olarak diğer objeler içinde yardımcı fonksiyonlar extension altında tanımlanmaktadır.

* Enum *
 
-> Tüm projeler için ortak olabilecek "Enum" classları buraya eklenmektedir.

* Dialog *

-> Tüm projeler için ortak olacak dialog sınıfları bı paket altına eklenmektedir. Bunlar dialog, calendar vb. dir, bu tip aynı özellikte kullanılacak 
dialoglar buraya eklenecektir.

* Model *

 -> Tüm projeler için ortak olacak modellerdi örnek; apiResponse ları BaseModeller olabilir. Burası genişletilebilir.
 
 * Utils *

-> Tüm projelerde ortak olacabilecek yardımcı sınıflar buraya eklenecektir.
