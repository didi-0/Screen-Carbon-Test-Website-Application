<template>
  <div class="manage">
    <el-dialog
      title="Add"
      :visible.sync="dialogVisible"
      width="50%"
      :before-close="handleClose"
    >
      <el-form
        :inline="true"
        :rules="rules"
        ref="form"
        :model="form"
        label-width="80px"
      >
        <el-form-item label="username" prop="username">
          <el-input
            v-model="form.username"
            placeholder="type username..."
          ></el-input>
        </el-form-item>

        <el-form-item label="usertype" prop="usertype">
          <el-select v-model="form.usertype" placeholder="type usertype...">
            <el-option label="Admin" value="Admin"></el-option>
            <el-option label="User" value="User"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="cancel">cancel</el-button>
        <el-button type="primary" @click="submit">confirm</el-button>
      </span>
    </el-dialog>
    <div class="manage-header">
      <el-button @click="dialogVisible = true" type="primary">
        + Add
      </el-button>
    </div>
    <div class="common-table">
      <el-table
        stripe
        height="90%"
        :data="tableData"
        style="width: 100%"
        empty-text="empty"
      >
        <el-table-column prop="username" label="username" width="520">
        </el-table-column>
        <el-table-column prop="usertype" label="usertype" width="520">
          <template slot-scope="scope">
            <span style="margin-left: 10px">{{
              scope.row.usertype === 0 ? "Admin" : "User"
            }}</span>
          </template>
        </el-table-column>
        <el-table-column label="Operations">
          <template slot-scope="scope">
            <el-button size="mini" @click="handleEdit(scope.row)"
              >Edit</el-button
            >
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.row)"
              >Delete</el-button
            >
          </template>
        </el-table-column>
      </el-table>
      <div class="pager">
        <el-pagination
          layout="prev, pager, next"
          :total="tableData.length"
          @current-change="handlePage"
        >
        </el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "User",
  data() {
    return {
      dialogVisible: false,
      form: {
        username: "",
        usertype: "",
      },
      rules: {
        username: [
          {
            required: true,
            message: "please type your username",
          },
        ],
        usertype: [
          {
            required: true,
            message: "please type your usertype",
          },
        ],
      },
      tableData: [
        { username: "Jack", usertype: 0 },
        { username: "Alice", usertype: 1 },
        { username: "Jone", usertype: 1 },
        { username: "Alex", usertype: 0 },
        { username: "Beccon", usertype: 0 },
        { username: "Beccon", usertype: 0 },
        { username: "Beccon", usertype: 0 },
        { username: "Beccon", usertype: 0 },
        { username: "Beccon", usertype: 0 },
        { username: "Beccon", usertype: 0 },
        { username: "Beccon", usertype: 0 },
        { username: "Beccon", usertype: 0 },
        { username: "Beccon", usertype: 0 },
        { username: "Beccon", usertype: 0 },
        { username: "Beccon", usertype: 0 },
      ],
      modelType: 0, //0 add, 1 edit
      total: 0, //tableData.length
      pageData: {
        page: 1,
        limit: 10,
      },
    };
  },
  methods: {
    submit() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          if (this.modelType === 0) {
            this.tableData.push();
          } else {
          }
          this.$refs.form.resetFields();
          this.dialogVisible = false;
        }
      });
    },
    handleClose() {
      this.$refs.form.resetFields();
      this.dialogVisible = false;
    },
    cancel() {
      this.handleClose();
    },
    handleEdit(row) {
      this.modelType = 1;
      this.dialogVisible = true;
      // this.form =
    },
    handleDelete(row) {
      this.$confirm(
        "This will permanently delete the row. Continue?",
        "Warning",
        {
          confirmButtonText: "OK",
          cancelButtonText: "Cancel",
          type: "warning",
        }
      )
        .then(() => {
          this.$message({
            type: "success",
            message: "Delete completed",
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "Delete canceled",
          });
        });
    },
    handleAdd() {
      this.modelType = 0;
      this.dialogVisible = true;
    },
    handlePage(page) {
      this.pageData.page = page;
    },
  },
  mounted: {},
};
</script>
<style lang="less" scoped>
.manage {
  height: 90%;
  .common-table {
    position: relative;
    height: calc(100% - 62px);
    .pager {
      position: absolute;
      bottom: 0px;
      right: 20px;
    }
  }
}
</style>