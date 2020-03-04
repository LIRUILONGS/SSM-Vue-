<template>
  <div style="width:80%;margin:0 10%">
    <div class="top-log-style">
      <!-- <div style="display: flex;justify-content: flex-start;"> -->
      <el-select v-model="logtype"
                 clearable
                 placeholder="日志类型"
                 icon="el-icon-plus"
                 style="margin-right: 10px">
        <el-option v-for="item in logtypes"
                   :key="item.id"
                   :label="item.logtypemsg"
                   :value="item.id"></el-option>
      </el-select>
      <el-date-picker v-model="beginDateScope"
                      type="monthrange"
                      align="left"
                      unlink-panels
                      value-format="yyyy-MM-dd"
                      range-separator="至"
                      start-placeholder="开始月份"
                      end-placeholder="结束月份"
                      :picker-options="pickerOptions">
      </el-date-picker>
      <el-button icon="el-icon-search"
                 type="primary"
                 style="margin: 0 10px"
                 @click="initlog">
        搜索
      </el-button>
       <el-select v-model="upoplog.logtype"
                 clearable
                 placeholder="日志类型"
                 icon="el-icon-plus"
                 style="margin-right: 10px">
        <el-option v-for="item in logtypes"
                   :key="item.id"
                   :label="item.logtypemsg"
                   :value="item.id"></el-option>
      </el-select>
      <el-input v-model="upoplog.hrname" placeholder="请输入操作人" style="width:10%;margin-right:10px"></el-input>
       <el-input v-model="upoplog.operate"  placeholder="请输入日志描述" style="width:30%;"  maxlength="20"  type="text"  show-word-limit></el-input>
         <el-button icon="el-icon-plus"
                 type="primary"
                 style="margin-left:10px"
                 @click="addlog">
        添加
      </el-button>
    </div>

    <div class="centon-log-style">
      <el-scrollbar style="height: 100%;">
        <div>
          <el-table :data="logs"
                    border
                    v-loading.fullscreen.lock="loading"
                    element-loading-spinner="fa fa-spinner fa-pulse fa-3x fa-fw"
                    stripe
                    height="690"
                    style="width:100%;height: 645px">

            <el-table-column prop="id"
                             label="编号"
                             width="55">
            </el-table-column>
            <el-table-column prop="logTypss.logtypemsg"
                             label="日志类型"
                             width="200">
            </el-table-column>
            <el-table-column prop="operate"
                             label="日志描述">
            </el-table-column>
            <el-table-column prop="hrname"
                             label="操作人"
                             width="200">
            </el-table-column>
            <el-table-column prop="adddate"
                             width="200"
                             label="日志时间">
            </el-table-column>
            <el-table-column label="操作">
              <template slot-scope="scope">
                <el-button size="mini"
                           @click="handleEdit( scope.row)">编辑</el-button>
                <el-button size="mini"
                           type="danger"
                           @click="handleDelete(scope.row)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </el-scrollbar>
      <el-dialog title="日志修改"
                 :visible.sync="dialogFormVisible">
        <el-form :model="oplog" :inline="true">
          <el-form-item label="日志类型">
            <el-select v-model="oplog.logtype"
                       clearable
                       placeholder="日志类型"
                       icon="el-icon-plus"
                       style="margin-right: 10px">
              <el-option v-for="item in logtypes"
                         :key="item.id"
                         :label="item.logtypemsg"
                         :value="item.id"></el-option>
            </el-select>

          </el-form-item>
          <el-form-item label="操做人" >
            <el-input v-model="oplog.hrname" ></el-input>
          </el-form-item>
          <el-form-item label="日志描述" >
            <el-input v-model="oplog.operate" style="width:500px"></el-input>
          </el-form-item>

        </el-form>
        <div slot="footer"
             class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary"
                     @click="updateoplog()">确 定</el-button>
        </div>
      </el-dialog>
    </div>
    <div class="bottom-log-style">
      <div>
       
      </div>
      <el-pagination style="margin-top: 10px;margin-right:0%"
                     background
                     @current-change="currentChange"
                     @size-change="sizeChange"
                     layout="sizes, prev, pager, next, jumper, ->, total, slot"
                     :total="total"
                     :page-sizes="[12,22,222,3333]">
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  name: "demo-3",
  data () {
    return {
      oplog: {
        logtype: "",
        operate: "",
        hrname: "",
      },
       upoplog: {
        logtype: "",
        operate: "",
        hrname: "",
      },
      loading: false,
      dialogFormVisible: false,
      total: 0,
      page: 1,
      size: 12,
      logtypes: {},
      logtype: '',
      logs: [],
      beginDateScope: null,
      multipleSelection: [],
      pickerOptions: {
        shortcuts: [{
          text: '本月',
          onClick (picker) {
            picker.$emit('pick', [new Date(), new Date()]);
          }
        }, {
          text: '今年至今',
          onClick (picker) {
            const end = new Date();
            const start = new Date(new Date().getFullYear(), 0);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近六个月',
          onClick (picker) {
            const end = new Date();
            const start = new Date();
            start.setMonth(start.getMonth() - 6);
            picker.$emit('pick', [start, end]);
          }
        }]
      }
    }
  },
  mounted () {
    this.initlog();
    this.inittypelog();
  },
  methods: {
    addlog(){
       console.log("添加"+this.upoplog)
      if(this.upoplog.logtype && this.upoplog.operate && this.upoplog.hrname){
      this.postRequest("/system/log/",this.upoplog).then(resp =>{
       
        this.upoplog = '';
        if(resp){
           this.initlog();
            this.upoplog = '';
        }
      })
      }else{
        
        this.$message({
          type: 'info',
          message: '输入信息为空'
        });
      }
    },
    handleEdit (data) {
      this.oplog = data;
      this.dialogFormVisible = true;
    },
    updateoplog(){
       this.putRequest("/system/log/",this.oplog).then(resp =>{
         if(resp){
           this.dialogFormVisible = false;
            this.initlog();
            this.oplog = '';
         }
       })
    },
    handleDelete (data) {
      this.$confirm('此操作将永久删除该日志, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.deleteRequest("/system/log/" + data.id).then(resp => {
          if (resp) {
            this.initlog();
          }
        })

      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    /*分页事件处理*/
    sizeChange (currentSize) {
      this.size = currentSize;
      this.initlog();
    },
    currentChange (currentPage) {
      this.page = currentPage;
      this.initlog();
    },
    initlog () {
      // this.loading = true;
      if (this.logtype) {
        this.$notify.success({
          title: '搜索讯息',
          message: '搜 索 日 志 中...',
          showClose: false,
          offset: 100,
          duration: 2000,
          customClass: 'fontclasssyslog'
        });
      }
      let url = "/system/log/?page=" + this.page + "&size=" + this.size;
      if (this.logtype) {
        url += "&logtype=" + this.logtype;
        this.logtype = '';
      }
      if (this.beginDateScope) {
        url += "&beginDateScope=" + this.beginDateScope;
        this.beginDateScope = null;
      }
      this.getRequest(url).then(resp => {
        if (resp) {
          this.logs = resp.data;
          this.total = resp.total;
          this.loading = false;
        }
      })
    },
    inittypelog () {
      this.getRequest("/system/log/logtype").then(resp => {
        if (resp) {
          this.logtypes = resp;
        }
      })
    }
  }
}
</script>

<style >
.fontclasssyslog {
  font-family: 站酷庆科黄油体;
}
.top-log-style {
  margin: 10px 0px;
  display: flex;
  justify-content: flex-start;
}
.centon-log-style {
  margin-top: 10px;
}
.bottom-log-style {
  display: flex;
  justify-content: space-between;
}
</style>