# LocaleLib
一个Bukkit插件前置，可以帮助你构建插件的语言系统/A plugin's library of Minecraft Bukkit, it can help you to build the language system.
# How to use?
LocaleLib#loadLocale(Plugin, String) - 第一个参数填写插件, 第二个填写路径，则为plugins/PluginName/路径
\n
然后使用Locale#asString，可以替换List消息和普通字符串, 会替换字符串中的{0}，{1}，以此类推。

