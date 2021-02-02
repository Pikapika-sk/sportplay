<template>
    <div class ="login_contanier">
        <!-- 登录 -->
        <div class = "login_box">
            <!-- logo -->
            <div class = "avator_box">
                <img src ="../assets/logo.png" alt/>
            </div>
            <!-- 表单区域 -->
            <el-form ref="loginFormRef" :rules="loginRules" :model="loginForm" class="login_Form" label-width="0">
                <!-- 用户名 -->
                <el-form-item prop="username">
                    <el-input v-model="loginForm.username" prefix-icon="iconfont icondenglu"></el-input>
                </el-form-item>
                <!-- 密码 -->
                <el-form-item prop="password">
                    <el-input v-model="loginForm.password" prefix-icon="iconfont iconmima"></el-input>
                </el-form-item>
                <!-- 按钮 -->
                <el-form-item class="btns" >
                    <el-button type="primary">登录</el-button>
                    <el-button type="info">重置</el-button>
                </el-form-item>
              </el-form>
        </div>

    </div>
</template>

<script>
export default {
  data(){
      return {
        // 表单数据
            loginForm:{
                username:"username",
                password:"password"
          },
          // 验证对象
          loginRules:{
            //   用户名校验
              username:[
                { required: true, message: '用户名为必填项', trigger: 'blur' }, //必填项验证
                { min: 5, max: 12, message: '长度在 5 到 12 个字符', trigger: 'blur' }//长度验证
          ],
              
            //   校验密码
              password:[
                { required: true, message: '用户密码为必填项', trigger: 'blur' }, //必填项验证
                { min: 6, max: 10, message: '长度在 6 ~ 10 个字符', trigger: 'blur' }//长度验证
              ],
          },
        
      }
  }
}
</script>

<style  scoped>
.login_contanier{
    /* 根节点样式 */
    background-color: #008c8c;
    height: 100%;
}
.login_box{
    width:450px;
    height: 300px;
    border-radius: 3px;
    background-color:#fff;
    position: absolute;
    left:50%;
    top:50%;
    transform: translate(-50%,-50%);
}
 .avator_box{
    height: 130px;
    width: 130px;
    border:1px solid #eee;
    border-radius: 50%;
    padding:10px;
    position: absolute;
    box-shadow: 0 0 2px #ddd;
    left:50%;
    transform:translate(-50%,-50%);
    background-color: rgb(159, 241, 224);
    
 }
 img{
        width:100%;
        height:100%;
        border-radius: 40%;
    }
.btns{
    display:flex;
    justify-content: flex-end;
}
.login_Form{
    position:absolute;
    bottom: 0%;
    width: 100%;
    padding: 0 10px;
    box-sizing: border-box;
}
</style>