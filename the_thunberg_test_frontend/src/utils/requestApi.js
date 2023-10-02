import request from "axios";
import config from "@/config";
import { Notification } from "element-ui";

const token = localStorage.getItem("token");

const service = request.create({
  baseURL: config.API_URL,
  headers: {
    Authorization:
      "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4YWNhZTM4NjdmMGFiODc0YjliYTA3YTAzNjVlMzQyYyIsInN1YiI6IjY0OTFkZmE2MjYzNDYyMDEwY2JkOTM4MiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.YcSaeP6W-ioTCjnJ2RYvMUoebQwt7omHlN1ljv-uk4E",
    accept: "application/json",
  },
});

service.interceptors.response.use(
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
            //     title: 'error: ' + res.code,
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

export default service