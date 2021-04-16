var vm = new Vue({
    el: '#userdiv',
    data:{
        userlist:[],
        user:{},
        deptList:[{postids:[]}],
        deptids:[],
        ids:[],
        userBean:{}
    },
    methods: {
        findAll: function () {
            var _this = this;
            axios.get('../user/getUserList.do').then(function (response) {
                console.log(response.data)
                _this.userlist = response.data
            })
        },
        fpDept: function (id) {
            var _this = this
            this.user={}
            this.deptList=[]
            axios.post('../user/findBuMenMsg.do?id='+id).then(function (response) {
                console.log(response.data)
                _this.user = response.data.userBean
                _this.deptList = response.data.deptBeans
                _this.deptids = response.data.ids
                document.getElementById("bumen").style.display="block"
            })

        },
        saveUserDept: function () {
            var _this = this
            alert("666:"+this.deptids)
            /*var deptArray = document.getElementsByName("deptids");
            for (var i = 0;deptArray.length;i++){
                if (deptArray[i].checked){
                    _this.ids.push(deptArray[i].value);
                }
            }*/
            axios.post('../user/saveUserDept.do?id='+_this.user.id,_this.deptids).then(function (response) {
                if (response.data=="yes"){
                    alert("添加成功")
                    _this.findAll()
                    document.getElementById("bumen").style.display="none"
                }
            })

        },
        fpPost:function (id) {
            var _this = this
            axios.get('/user/fpPost.do?id='+id).then(function (response) {
                _this.userBean = response.data
                _this.deptList = response.data.dlist
                console.log(response.data)
                document.getElementById("postTitle").style.display="block"
            })
        },
        saveUserPost:function () {
            // 修改职位
            // 把职位付给userBean
            var _this = this
            this.userBean.dlist = this.deptList
            axios.post('/user/saveUserPost.do',_this.userBean).then(function (response) {
                if (response.data=="yes"){
                    alert("分配成功！")
                    document.getElementById("postTitle").style.display="none"
                }else{
                    alert("分配失败！")
                    document.getElementById("postTitle").style.display="none"
                }
            })
        }
    }
})
vm.findAll();