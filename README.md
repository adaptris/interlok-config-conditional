# interlok-config-conditional

[![GitHub tag](https://img.shields.io/github/tag/adaptris/interlok-config-conditional.svg)](https://github.com/adaptris/interlok-config-conditional/tags) ![license](https://img.shields.io/github/license/adaptris/interlok-config-conditional.svg) [![Build Status](https://travis-ci.org/adaptris/interlok-config-conditional.svg?branch=develop)](https://travis-ci.org/adaptris/interlok-config-conditional) [![CircleCI](https://circleci.com/gh/adaptris/interlok-config-conditional/tree/develop.svg?style=svg)](https://circleci.com/gh/adaptris/interlok-config-conditional/tree/develop) [![codecov](https://codecov.io/gh/adaptris/interlok-config-conditional/branch/develop/graph/badge.svg)](https://codecov.io/gh/adaptris/interlok-config-conditional) [![Total alerts](https://img.shields.io/lgtm/alerts/g/adaptris/interlok-config-conditional.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/adaptris/interlok-config-conditional/alerts/) [![Language grade: Java](https://img.shields.io/lgtm/grade/java/g/adaptris/interlok-config-conditional.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/adaptris/interlok-config-conditional/context:java)

Because you know you want to do this; though that's a lot of nested conditions.

```
 <if-then-otherwise>
   <condition class="and">
    <metadata>
     <operator class="not-null"/>
     <metadata-key>key1</metadata-key>
    </metadata>
    <or>
     <expression>
      <algorithm>(%message{key1} - 10) == %message{key2}</algorithm>
     </expression>
     <metadata>
      <operator class="equals">
        <value>myValue</value>
      </operator>
      <metadata-key>key2</metadata-key>
     </metadata>
    </or>
    <function>
      <definition><![CDATA[function evaluateScript(message) { return message.getMetadataValue('mykey').equals('myvalue');}]]></definition>
    </function>
   </condition>
   <then>
    <service class="log-message-service">
     <unique-id>26eab97b-9612-46e4-9c28-6c9536b07f16</unique-id>
     <log-level>DEBUG</log-level>
    </service>
   </then>
   <otherwise>
    <service class="log-message-service">
     <unique-id>f94026b4-fee5-4477-b927-28b1d5765dc8</unique-id>
     <log-level>DEBUG</log-level>
    </service>
   </otherwise>
  </if-then-otherwise>
```

Which essentially boils down to :

```
<if-then-otherwise>
  <some-kind-of-condition/>  // where <or> and <and> will wrap other conditions.
  <then>
    <service class="service-list">
    </service>
  </then>
  <otherwise>
    <service class="service-list">
    </service>
  </otherwise>
</if-then-otherwise>
```

No; you don't get elseif.
