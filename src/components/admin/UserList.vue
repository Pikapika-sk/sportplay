<template>
  <div>
    用户列表
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>权限管理</el-breadcrumb-item>
      <el-breadcrumb-item>用户列表</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 用户列表主体 -->
    <el-card>
      <el-row :gutter="25"
        ><!--间隙-->
        <el-col :span="10">
          <!--列宽-->
          <el-input
            placeholder="请输入搜索用户名"
            v-model="queryInfo.query"
            clearable
            @clear="getUserList()"
          >
            <el-button
              slot="append"
              icon="el-icon-search"
              @click="getUserList"
            ></el-button>
          </el-input>
        </el-col>
        <el-col :span="2">
          <el-button type="primary" @click="addDialogVisible = true"
            >添加用户</el-button
          ></el-col
        >
      </el-row>

      <!-- 用户列表 -->
      <!-- 用户列表  border 边框 stripe 隔行变色 -->
      <el-table :data="UserList" stripe border>
        <el-table-column type="index"></el-table-column
        ><!--索引列-->
        <el-table-column label="姓名" prop="username"></el-table-column>
        <el-table-column label="邮箱" prop="email"></el-table-column>
        <el-table-column label="密码" prop="password"></el-table-column>
        <el-table-column label="角色" prop="role"></el-table-column>
        <el-table-column label="状态" prop="state">
          <!--作用域插槽 scope.row 存储了当前行的信息 -->
          <template slot-scope="scope"
            ><!--数据模板-->
            <el-switch
              v-model="scope.row.state"
              @change="userStateChanged(scope.row)"
            ></el-switch>
          </template>
        </el-table-column>
        <!--自定义插槽-->
        <el-table-column label="操作">
          <template slot-scope="scope">
            <!-- 修改 -->
            <el-button
              type="primary"
              icon="el-icon-edit"
              size="mini"
              @click="showEditDialog(scope.row.id)"
            ></el-button>
            <!-- 删除 -->
            <el-button
              type="danger"
              icon="el-icon-delete"
              size="mini"
              @click="deleteUser(scope.row.id)"
            ></el-button>
            <!-- 权限 -->
            <el-tooltip
              effect="dark"
              content="分配权限"
              placement="top-start"
              :enterable="false"
              ><!--文字提示 enterable 隐藏-->
              <el-button
                type="warning"
                icon="el-icon-setting"
                size="mini"
              ></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>
      <div>
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="queryInfo.pageNum"
          :page-sizes="[1, 2, 5, 100]"
          :page-size="queryInfo.pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
        >
        </el-pagination>
      </div>
    </el-card>
    <el-dialog
      title="添加用户"
      :visible.sync="addDialogVisible"
      width="50%"
      @close="addDialogClosed"
    >
      <el-form
        :model="addForm"
        :rules="addFormRules"
        ref="addFormRef"
        label-width="70px"
      >
        <el-form-item label="用户名" prop="username">
          <el-input v-model="addForm.username"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="addForm.password"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="addForm.email"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addUser">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 修改对话框 -->
    <el-dialog
      title="修改用户信息"
      :visible.sync="editDialogVisible"
      width="50%"
      @close="editDialogClosed"
    >
      <el-form
        :model="editForm"
        :rules="editFormRules"
        ref="editFormRef"
        label-width="70px"
      >
        <el-form-item label="用户名" prop="username">
          <el-input v-model="editForm.username" disabled></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="editForm.password"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="editForm.email"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editUserInfo">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // 查询信息实体
      queryInfo: {
        query: '',
        pageNum: 1,
        pageSize: 5,
      },
      UserList: [],
      total: 0, //总记录数
      addDialogVisible: false, //对话框显示状态
      addForm: {
        username: '',
        password: '',
        email: '',
      },

      editForm:{
        username: '',
        password: '',
        email: '',
 
      },
      editDialogVisible:false,


      //添加表单验证
      addFormRules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' }, //必填项验证
          {
            min: 5,
            max: 8,
            message: '长度在 5 到 8 个字符',
            trigger: 'blur',
          }, //长度验证
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' }, //必填项验证
          {
            min: 5,
            max: 8,
            message: '长度在 5 到 8 个字符',
            trigger: 'blur',
          }, //长度验证
        ],
        email: [
          { required: true, message: '请输入邮箱', trigger: 'blur' }, //必填项验证
          {
            min: 5,
            max: 15,
            message: '请输入正确邮箱地址',
            trigger: 'blur',
          }, //长度验证
        ],
      },
      //修改表单验证
      editFormRules:{
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' }, //必填项验证
          {
            min: 5,
            max: 8,
            message: '长度在 5 到 8 个字符',
            trigger: 'blur',
          }, //长度验证
        ],
        email: [
          { required: true, message: '请输入邮箱', trigger: 'blur' }, //必填项验证
          {
            min: 5,
            max: 15,
            message: '请输入正确邮箱地址',
            trigger: 'blur',
          }, //长度验证
        ],
      }
    }
  },
  created() {
    this.getUserList()
  },
  methods: {
    async getUserList() {
      const { data: res } = await this.$http.get('alluser', {
        params: this.queryInfo,
      })
      this.UserList = res.data
      this.total = res.numbers
    },
    // 监听pageSize改变的事件
    handleSizeChange(newSize) {
      this.queryInfo.pageSize = newSize
      this.getUserList() // 数据发生改变重新申请数据
    },
    // 监听pageNum改变的事件
    handleCurrentChange(newPage) {
      this.queryInfo.pageNum = newPage
      this.getUserList() // 数据发生改变重新申请数据
    },
    async userStateChanged(userInfo) {
      // 这里 是` 符号不是'
      const { data: res } = await this.$http.put(
        `userstate?id=${userInfo.id}&state=${userInfo.state}`
      )

      if (res != 'success') {
        userInfo.id = !userInfo.id
        return this.$message.error('操作失败')
      }
      this.$message.success('操作成功')
    },
    //监听添加用户
    addDialogClosed() {
      this.$refs.addFormRef.resetFields()
    },
    addUser() {
      this.$refs.addFormRef.validate(async (valid) => {
        if (!valid) return
        const { data: res } = await this.$http.post('addUser', this.addForm)
        if (res != 'success') {
          return this.$message.error('操作失败')
        } else {
          this.$message.success('操作成功')
          this.addDialogVisible = false
          this.getUserList()
        }
      })
    },
    async deleteUser(id) {
      const confirmResult = await this.$confirm(
        '此操作将永久删除,是否继续',
        '提示',
        {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
        }
      ).catch((err) => err)
      if (confirmResult != 'confirm') {
        return this.$message.info('已取消删除')
      }
      const { data: res } = await this.$http.delete('deleteUser?id=' + id)
      if (res != 'success') {
        return this.$message.error('删除失败')
      } else {
        this.$message.success('删除成功')
        this.getUserList()
      }
    },
    // 显示对话框
    async showEditDialog(id){
      const { data: res } = await this.$http.get("getupdate?id="+id)
      this.editForm =res;
      this.editDialogVisible =true;
    },
    // 关闭窗口
    editDialogClosed(){
      this.$refs.editFormRef.resetFields();
    },
    // 确认修改
    editUserInfo(){
  this.$refs.editFormRef.validate(async valid => {
      if(!valid)return ;
      const{data:res} =await this.$http.put("editUser",this.editForm);
      if(res!="success")return this.$message.error("操作失败")
      this.$message.success("操作成功")
      this.editDialogVisible =false;
      this.getUserList();
    })



    },
  },
}
</script>

<style scoped>
.el-breadcrumb {
  margin-bottom: 30px;
  font-size: 23px;
}
</style>
