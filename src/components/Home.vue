<template>
  <el-container class="home-container">
    <el-header>
      <div class="dd">
        <img src="../assets/sport.png" class="sportimg" />
        <span class="span"> 个人运动平台</span>
      </div>
      <el-button type="info" @click="logout" class="out-button"
        >安全退出</el-button
      >
    </el-header>
    <el-container>
      <el-aside :width="isCollapse ? '64px' : '200px'">
        <!--  伸缩宽度判断 -->
        <div class="toggle-button" @click="toggleCollapase">
          |||
        </div>
        <!-- collapse-transition:是否开启折叠动画 -->
        <!-- router="true" 变成对应的id -->
        <el-menu
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#409eff"
          unique-opened
          :collapse="isCollapse"
          :collapse-transition="false"
          :router="true"
          :default-active= "activePath"
        >
          <el-submenu
            :index="item.path"
            v-for="item in menuList"
            :key="item.id"
          >
            <template slot="title">
              <!-- i标签：图标  数据绑定-->
              <i :class="iconsObject[item.id]"></i>
              <span>{{ item.title }}</span>
            </template>
            <el-menu-item
              :index="it.path + ''"
              v-for="it in item.sList"
              :key="it.id"
              @click="saveNavState(it.path)"
            >
              <template slot="title">
                <i :class="iconsObject[it.id]"></i>
                <span>{{ it.title }}</span>
              </template>
            </el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>

      <el-container>
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </el-container>
</template>

<script>
export default {
  data() {
    return {
      menuList: [],
      isCollapse: false,
      iconsObject: {
        // 也可以用数据库读取路径，不必一个个设
        '100': 'iconfont iconguanliyuan',
        '200': 'iconfont iconsport',
        '101': 'iconfont icondenglu',
        '102': 'iconfont iconmima',
        '103': 'iconfont iconsport',
        '104': 'iconfont iconshangpin',
        '201': 'iconfont iconshu',
        '202': 'iconfont iconkaluli',
        '203': 'iconfont iconshiwu',
        '204': 'iconfont icondenglu',
      },
      activePath:'/welcome'//默认路径
    }
  },
  // 要放在methods的外面
  created() {
    // 查询aside菜单列表
    this.getMenuList();
    this.activePath= window.sessionStorage.getItem('activePath');
  },
  methods: {
    logout() {
      window.sessionStorage.clear()
      this.$router.push('/login')
    },
    // 获取导航菜单方法
    async getMenuList() {
      const { data: res } = await this.$http.get('menus')
      console.log(res)
      if (res.flag != 200) {
        return this.$message.errror('获取列表失败')
      }
      this.menuList = res.menus
    },
    toggleCollapase() {
      this.isCollapse = !this.isCollapse
    },
    //导航栏，路径保存
    saveNavState(activePath){
      window.sessionStorage.setItem('activePath',activePath);//存放到session里
      this.activePath =activePath;
    }
  },
}
</script>
<style scoped>
.el-header {
  background-color: #373d41;
  display: flex;
  justify-content: space-between;
  padding-left: 0%;
  align-items: center;
  color: #fff;
  font-size: 20px;
}
.dd {
  display: flex;
  align-items: center;
}
.sportimg {
  height: 50px;
  width: 50px;
}
.span {
  display: flex;
  left: 50px;
  margin-left: 15px;
}
.el-aside {
  background-color: #333744;
}
.el-menu {
  border-right: none;
}
.el-main {
  background-color: #eaedf1;
}
.home-container {
  height: 100%;
}
.toggle-button {
  background-color: #4a5064;
  font-size: 10px;
  line-height: 24px;
  color: white;
  text-align: center;
  letter-spacing: 0.2em;
  cursor: pointer; /*  鼠标指针变成手 */
}
.out-button {
  align-items: center;
}
</style>
