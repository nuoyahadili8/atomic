<!-- 表格搜索组件 -->
<template>
    <div class="search">
        <Input placeholder="请选择列名并输入内容..." v-model="searchContent">
            <Select slot="prepend" style="width: 80px" v-model="searchColumn">
                <Option v-for="column in searchColumns"
                        v-if="validColumn(column)"
                        :value="column.key"
                        :key="column.key">
                    {{column.title}}
                </Option>
            </Select>
            <Button type="primary" slot="append" icon="search" @click="handleSearch" />
        </Input>
    </div>
</template>

<script>
    export default{
        name: 'TableSearch',
        props: [
            'tableData',
            // 可参照iview table组件的column参数, https://www.iviewui.com/components/table#column
            'searchColumns'
        ],
        data () {
            return {
                searchContent: '', // 当前搜索内容
                tableDataClone: [], // 表格数据克隆
                // 当前搜索的表格列的key，默认为searchColumns的第一个元素的key
                'searchColumn': this.searchColumns[0]['key']
            };
        },
        methods: {
            validColumn (c) {
                return c.key !== undefined && c.title !== undefined;
            },
            // 表格搜索函数，可支持多列搜索
            search (data, argumentObj) {
                let res = data;
                let dataClone = data;
                for (let argu in argumentObj) {
                    if (argumentObj[argu].length > 0) {
                        res = dataClone.filter(d => {
                            return d[argu].indexOf(argumentObj[argu]) > -1;
                        });
                        dataClone = res;
                    }
                }
                return res;
            },
            handleSearch () {
                var argumentObjStr = '{"' + this.searchColumn + '": "' + this.searchContent + '"}'; // 拼接json
                var argumentObj = JSON.parse(argumentObjStr); // 转为对象
                var res = this.search(this.tableDataClone, argumentObj); // 执行搜索，获取搜索结果
                this.$emit('update:tableData', res); // 更新表格数据为搜索结果

                console.log(res);
            }
        },
        watch: {
            // 因父组件表格数据通常存在异步操作
            // 需监听props以得到正确的数据
            tableData (newTableData) {
                if (newTableData.length > 0 && this.tableDataClone.length == 0) {
                    this.tableDataClone = newTableData;
                }
            }
        }
    };
</script>