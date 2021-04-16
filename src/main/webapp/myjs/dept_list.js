var vm= new Vue({
    el:'#app',
    data:{
        deptlist:[],
        page:{},
        pageNum:1,
        pageSize:5,
        search:{},
        deptEntity:{},
        postList:[],
        checkedPost:[]
    },
    methods:{
        findAll:function () {
            var _this = this
            axios.post('../dept/getAllDept.do?pageNum='+this.pageNum+'&pageSize='+this.pageSize,this.search).then(function (response) {
                console.log(response.data)
                _this.deptlist = response.data.list
                _this.page = response.data
                _this.pageNum = response.data.currentPage
                _this.pageSize=response.data.pageSize
            })
        },
        packing:function (pageNum) {
            this.pageNum = pageNum
            this.findAll();
        },
        fpPost:function (id) {
            var _this = this
            axios.post('/dept/fpPost.do?id='+id).then(function (response) {
                _this.deptEntity= response.data.deptBean
                _this.postList = response.data.postBeans
                _this.checkedPost = response.data.ids
                document.getElementById("postTitle").style.display="block"
            })
        },
        saveDeptPost:function () {
            var _this = this
            axios.post('/dept/saveDeptPost.do?id='+_this.deptEntity.id,_this.checkedPost).then(function (response) {
                if (response.data=="yes"){
                    alert("修改成功！")
                    document.getElementById("postTitle").style.display="none"
                }else{
                    alert("修改失败！")
                }
            })
        }
    }
})
vm.findAll();