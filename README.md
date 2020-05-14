# GroupAdministrator
简单的mirai群管插件  
此插件处于开发阶段，未来将添加更多功能。
## Configuration file
插件启动后会生成/plugins/GroupAdministrator/config.yml  
`key_word`：如果群成员的消息内包含该list中的词，该成员的消息将会被撤回，并且将禁言该成员600秒  
`accept_word`：如果加群申请内包含该list中的词（大小写不敏感），将会自动同意该申请
## Command
`/gareload`：重载config.yml
