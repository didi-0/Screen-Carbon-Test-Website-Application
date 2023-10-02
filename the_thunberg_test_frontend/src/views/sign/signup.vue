<template>
  <div class="content">
    <el-card class="box-card">
      <div slot="header" class="clearfix d-flex justify-content-center">
        <h3>Register</h3>
      </div>
      <el-form action="##" :model="ruleForm" ref="ruleForm">
        <el-form-item
          class="input-text clearFix"
          prop="username"
          :rules="[{ required: true, message: 'cannot be null' }]"
        >
          <!-- <span></span> -->
          <el-input
            clearable
            v-model="ruleForm.username"
            type="text"
            placeholder="username"
          >
            ><i class="el-icon-user" slot="prepend"></i>
          </el-input>
        </el-form-item>
        <div class="input-text clearFix">
          <el-form-item
            class="input-text clearFix"
            prop="password"
            :rules="rules.password"
          >
            <el-input
              show-password
              v-model="ruleForm.password"
              type="text"
              placeholder="password"
            >
              <i class="el-icon-lock" slot="prepend"></i>
            </el-input>
          </el-form-item>
          <el-form-item
            class="input-text clearFix"
            prop="checkPass"
            :rules="rules.checkPass"
          >
            <el-input
              show-password
              v-model="ruleForm.checkPass"
              type="text"
              placeholder="password"
            >
              <i class="el-icon-lock" slot="prepend"></i>
            </el-input>
          </el-form-item>
        </div>
        <!-- <div class="setting clearFix mt-3">
          <span class="forget">forget password?</span>
        </div> -->
        <div class="d-flex justify-content-center mt-3">
          <button @click="userRegister('ruleForm')" class="btn-dark btn-sm">
            Register
          </button>
        </div>
        <div class="d-flex justify-content-center mt-3">
          <router-link class="register" to="/signin"
            >Login&nbsp;&nbsp;in</router-link
          >
        </div>
      </el-form>
    </el-card>
  </div>
</template>
    
<script>
import Cookie from "js-cookie";
import { serverUserLogin } from "@/api/film";
import { serverUserRegister } from "@/api/film";
export default {
  data() {
    return {
      ruleForm: {
        username: "",
        password: "",
        checkPass: "",
      },
      rules: {
        password: [
          { required: true, message: "Please type password", trigger: "blur" },
          {
            trigger: "blur",
            validator: (rule, value, callback) => {
              var passwordreg =
                /(?=.*\d)(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{8,20}/;
              if (!passwordreg.test(value)) {
                callback(
                  new Error(
                    "Password must consist of a combination of digits, letters, and special characters, and be 8-20 characters in length."
                  )
                );
              } else {
                callback();
              }
            },
          },
        ],
        checkPass: [
          { required: true, message: "Please type password", trigger: "blur" },
          {
            trigger: "blur",
            validator: (rule, value, callback) => {
              if (value !== this.ruleForm.password) {
                callback(new Error("Passwords do not match."));
              } else {
                callback();
              }
            },
          },
        ],
      },
    };
  },
  methods: {
    userRegister(ruleForm) {
      this.$refs[ruleForm].validate((valid) => {
        if (valid) {
          const requestData = {
            username: this.ruleForm.username,
            password: this.ruleForm.password,
          };
          serverUserLogin(requestData).then((res) => {
            Cookie.set("token", res.data);
            serverUserRegister(requestData).then((res) => {
              console.log(res);
              if (res.data === 1) {
                alert("Register Success!");
                this.$router.push("/");
              } else {
                alert("Register Fail!");
              }
            });
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
  },
};
</script>

<style>
.content {
  position: relative;
  width: 100%;
  height: 75vh;
}

.box-card {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
</style>