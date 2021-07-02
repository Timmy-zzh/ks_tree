##### JetPackt



- MVVM
- ViewModel
  - LiveData：生命周期感知的数据持有者
  - 在View中将Activity与ViewModel进行绑定
  - Model层获取到数据后，设置到VM的LiveData中，
  - LiveData数据有更新后，调用View层的观察者Observe的onChange方法