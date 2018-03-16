<template>
    <div class="userManager">
        <Row type="flex">
            <Col :md="6" order="1">
                <Card dis-hover>
                    <div slot="title">
                        <Input size="large" placeholder="搜索用户.." icon="search"/>
                    </div>

                    <div class="classification">
                        <h4 class="title"><Icon type="funnel" /> 按类型筛选</h4>
                        <div style="margin-top:10px" class="item">
                            <Icon type="person-stalker" /> 所有用户
                        </div>
                        <div class="item">
                            <Icon type="happy-outline" /> 最近添加的用户
                        </div>
                        <div class="item">
                            <Icon type="sad-outline" /> 未分配角色的用户
                        </div>
                        <div class="item">
                            <Icon type="eye-disabled" /> 被禁用的用户
                        </div>
                    </div>
                    <br />

                    <div class="classification">
                        <h4 class="title"><Icon type="funnel" /> 按部门筛选</h4>
                        <Select style="margin-top:10px" placeholder="选择父级部门">
                            <Option value="北京总公司">北京总公司</Option>
                            <Option value="上海分公司">上海分公司</Option>
                            <Option value="深圳分公司">深圳分公司</Option>
                        </Select>
                        <div class="section item">销售部</div>
                        <div class="section item">市场研发部</div>
                        <div class="section item">售后服务部</div>
                        <div class="section item">产品设计部</div>
                        <div class="section item">质量管理部</div>
                        <div class="section item">行政人事部</div>
                        <div class="section item">开发部</div>

                        <a href="#"><Icon type="plus-circled"/> 在此创建部门</a>
                    </div>
                </Card>
            </Col>
            <Col :md="1" order="2" />
            <Col :md="17" order="3">
                <Card dis-hover>
                    <p slot="title">产品设计部 & 用户管理</p>
                    <div slot="extra">
                        <Button type="success" icon="plus-circled" size="small">创建用户</Button>
                        <Button type="ghost" icon="social-buffer" size="small">为选中用户分配角色</Button>
                    </div>
                    <Table border="false"
                            :data="tableData1"
                            :columns="tableColumns1"
                            stripe />

                    <div style="margin-top: 10px;">
                        <Page :total="100" :current="1" @on-change="changePage"></Page>
                    </div>
                </Card>
            </Col>
        </Row>
    </div>
</template>

<script>
export default {
    data () {
        return {
            tableData1: this.mockTableData1(),
            tableColumns1: [
                {
                    title: '用户名',
                    key: 'username'
                },
                {
                    title: '昵称',
                    key: 'nickname'
                },
                {
                    title: '邮箱',
                    key: 'email'
                },
                {
                    title: '状态',
                    key: 'status',
                    render: (h, params) => {
                        //用户状态(0启用,1禁用)
                        const row = params.row;
                        const color = row.status === 0 ? 'green' : 'red';
                        const text = row.status === 0 ? '启用' : '禁用';
                        return h('Tag', {
                            props: {
                                type: 'dot',
                                color: color
                            }
                        }, text);
                    }
                },
                {
                    title: '操作',
                    key: 'action',
                    fixed: 'right',
                    align: 'center',
                    width: 130,
                    render: (h, params) => {
                        return h('div', [
                            h('Button', {
                                props: {
                                    type: 'primary',
                                    size: 'small'
                                },
                                style: {
                                    marginRight: '5px'
                                },
                                on: {
                                    click: () => {
                                        this.editRow(params.index)
                                    }
                                }
                            }, '编辑'),
                            h('Button', {
                                props: {
                                    type: 'error',
                                    size: 'small'
                                },
                                on: {
                                    click: () => {
                                        this.removeRow(params.index)
                                    }
                                }
                            }, '删除')
                        ]);
                    }
                }
            ]
        };
    },
    methods: {
        mockTableData1 () {
            let data = [];
            for (let i = 0; i < 10; i++) {
                data.push({
                    username: 'user' + Math.round(Math.random() * 10000),
                    nickname: '用户' + Math.round(Math.random() * 10000),
                    email: 'mail'+ Math.round(Math.random() * 10000) +'@gmail.com',
                    status: Math.round(Math.random())
                });
            }
            return data;
        },
        editRow (index) {
            this.$Modal.info({
                title: '用户信息',
                content: `
                    <p> 用户名：`+ this.tableData1[index].username +`</p>
                    <p> 昵称：`+ this.tableData1[index].nickname +`</p>
                    <p> 邮箱地址：`+ this.tableData1[index].email +`</p>
                `
            })
        },
        removeRow (index) {
            this.tableData1.splice(index, 1);
        },
        changePage () {
            this.tableData1 = this.mockTableData1();
        }
    }
};
</script>

<style lang="less">
    .userManager {
        .classification {
            .title {
                font-weight: normal !important;
                border-bottom: 1px solid #e3e8ee !important;
            }
            .item {
                /*font-size: 15px;*/
                cursor: pointer;
                padding: 8px 0px 8px 22px;
                border-radius: 2px;
                &:hover, &:active {
                    border-left: 2px solid #33b976;
                    background: rgba(213, 223, 238, 0.35);
                }
            }
            .section {
                padding: 8px 0px 8px 5px !important;
            }
        }
    }
</style>
