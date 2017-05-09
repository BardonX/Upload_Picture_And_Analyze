编码：gbk
功能：选择一种图片上次到web服务器，调用网络接口，判断图片的分类。有三种分类：色情、性感、正常
备注1：Upload类中的upload()方法存在问题，uploadFiles()方法可正常使用

备注2：face.jsp用到其他界面，有待改进，但能够上传图片，js未将返回的json数据解析清楚。
    face.jsp文件传递路径，主要是点击上传按钮，转到upload.jsp，
           upload.jsp调用Upload类的uploadFiles()方法，上传到服务器，并返回文件名至face.jsp
             随即执行faceDo()方法，Ajax传递数据到faceData.jsp,获取信息返回给face.jsp解析
   
备注3：WebRoot文件夹下的img、js文件夹提供图片和js文件。WEB-INF文件夹中的lib文件中的jar包至关重要。后面是主页face.jsp、upload.jsp、faceData.jsp

备注4、Upload类是实现文件上传至服务器功能、Query类调用网络接口，返回String信息