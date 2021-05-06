- 获取线程ID、activityID、内核ID

~~~java
        /**
         * Returns the identifier of this process's user.
         * 返回此进程的用户的标识符。
         */
        Log.e(TAG, "Process.myUid() = " + android.os.Process.myTid());

        /**
         * Returns the identifier of this process, which can be used with
         * killProcess and sendSignal.
         * 返回此进程的标识符，可用于进程和发送信号。
         */
        Log.e(TAG, "Process.myPid() = " + android.os.Process.myPid());

        /**
         * Returns the identifier of the calling thread, which be used with
         * setThreadPriority(int, int).
         * 返回调用线程的标识符，该标识符与StTeRead优先级（int，int）。
         */
        Log.e(TAG, "Process.myTid() = " + android.os.Process.myTid());

        /**
         * Returns the thread's identifier. The ID is a positive long generated
         * on thread creation, is unique to the thread, and doesn't change
         * during the lifetime of the thread; the ID may be reused after the
         * thread has been terminated.
         * 返回线程的标识符。ID是正长生成的关于线程创建，对于线程是唯一的，并且不会改变。
         * 在线程的生存期内，ID可以在线程已被终止。
         */

        //返回当前线程的id
        Log.e(TAG, "Thread.currentThread().getId() = "
                + Thread.currentThread().getId());
        //返回主线程的id
        Log.e(TAG, "getMainLooper().getThread().getId() = "
                + getMainLooper().getThread().getId());

        //返回当前应用的主线程id
        Log.e(TAG,
                        "((getApplication().getMainLooper()).getThread()).getId() = "
                                + ((getApplication().getMainLooper()).getThread())
                                .getId());

        /**
         * Return the identifier of the task this activity is in. This
         * identifier will remain the same for the lifetime of the activity.
         * 返回此活动正在执行的任务的标识符。这个标识符对于活动的生存期将保持不变。
         */
        //返回activity任务栈的id
        Log.e(TAG, "getTaskId() = " + getTaskId());

        /**
         * The kernel user-ID that has been assigned to this application;
         * currently this is not a unique ID (multiple applications can have the
         * same uid).
         * 已分配给该应用程序的内核用户ID；这不是一个唯一的ID（多个应用程序可以有相同的UID）。
         */
        Log.e(TAG, "getApplicationInfo().uid = " + getApplicationInfo().uid);

        /**
         * The name of the process this application should run in. From the
         * "process" attribute or, if not set, the same as packageName.
         * 此应用程序应运行的进程的名称。从“进程”属性，或如果没有设置，与PACKAGEName相同。
         */
        Log.e(TAG, "getApplicationInfo().processName = "
                + getApplicationInfo().processName);

        /**
         * 得到当前activity的信息
         */
        Log.e(TAG, "Activity.toString:"+this.toString());

        new Thread(new Runnable() {

            @Override
            public void run() {
                //返回当前线程的id
                // TODO Auto-generated method stub
                Log.e(TAG, "Thread.currentThread().getId() = "
                        + Thread.currentThread().getId());
            }
        }).start();
~~~

