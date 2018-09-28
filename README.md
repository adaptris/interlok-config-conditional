# interlok-config-conditional [![GitHub tag](https://img.shields.io/github/tag/adaptris/interlok-config-conditional.svg)](https://github.com/adaptris/interlok-config-conditional/tags) ![license](https://img.shields.io/github/license/adaptris/interlok-config-conditional.svg) [![Build Status](https://travis-ci.org/adaptris/interlok-config-conditional.svg?branch=develop)](https://travis-ci.org/adaptris/interlok-config-conditional) [![codecov](https://codecov.io/gh/adaptris/interlok-config-conditional/branch/develop/graph/badge.svg)](https://codecov.io/gh/adaptris/interlok-config-conditional)

Because you know you want to do this; though that's a lot of nested conditions.

```
  <if-then-otherwise>
   <and>
    <metadata>
     <not-null/>
     <metadata-key>key1</metadata-key>
    </metadata>
    <or>
     <expression>
      <algorithm>(%message{key1} - 10) == %message{key2}</algorithm>
     </expression>
     <metadata>
      <equals>
       <value>myValue</value>
      </equals>
      <metadata-key>key2</metadata-key>
     </metadata>
    </or>
    <function>
     <definition><![CDATA[function evaluateScript(message) { return message.getMetadataValue('mykey').equals('myvalue');}]]></definition>
    </function>
   </and>
   <then>
    <service class="log-message-service">
     <unique-id>9d95cd07-a320-44a9-b0e0-d33c5d2a43f1</unique-id>
     <log-level>DEBUG</log-level>
    </service>
   </then>
   <otherwise>
    <service class="log-message-service">
     <unique-id>43de299a-ea19-4c8a-86a1-7d560ada3e81</unique-id>
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
