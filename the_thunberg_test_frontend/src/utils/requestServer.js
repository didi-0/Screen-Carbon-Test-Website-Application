import request from "axios";
import config from "@/config";
import { Notification } from "element-ui";

const serviceServer = request.create({
  baseURL: config.SERVER_URL,
  headers: {
    accept: "application/json",
  },
});

serviceServer.interceptors.response.use(
    response => {
        const res = response.data;
        if (res.success === true) {
            if (res.msg !== null) {
                Notification.success({
                    title: 'Success ',
                    message: res.msg,
                    type: 'success'
                });
            }

        } else {
            // Notification.error({
            //     title: 'Error: ' + res.code,
            //     message: res.msg
            // });
        }
        return res
    },

    error => {
        console.log('err' + error);
        return Promise.reject(error)
    }
);

export default serviceServer