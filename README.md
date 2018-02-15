# Responsive Design language

A small markup designed to create layouts which are responsive on their own, whitout the help of outside resources (aka. css)

At the Moment nothing is set in stone, but here's the guideline on what I'd like to achive. 

## Main tags

```
<l>
<c:100px:200px>card1</c>
<c:100px:200px>card1.2</c>
<c:100px:200px>card1.3</c>
<c:100w:200px::10px>cardwide</c>
</l>
```
* l: Starts a new layer
* c: defines a new "card"

Everything is collected into layers, which are rendered on the top of each other. Currently all layers are responsive on their own.
Cards are the default unit of content, they can move around based on the size of the screen.

It has a strict syntax but it can express the information we need to create a responsive layout.

Because it needs no outside resource to make it responsive, it can be implemented to create gui-s in a variety of different situations,
where some solutions (html, xml) could create bloat.

### Tag Syntax

All the resizeable tags follow the this syntax:
```
<{tag}:{width}:{heigth}:{left}:{top}>{text}</{tag}>
```
Anything can be left out except the tag.

Example:
If we want to leave out left and we want to have a top attribute

```
<c:100w:200px::10px>cardwide</c>
```

Currently we use standard c like multiline comments, for convenience.

```
/*<c:100w:200px>cardwide</c>*/ 
```
We put down 2 ":" characters, and just leave out the left attribute from in between.

### Media

Media quarries not currently implemented but the plan follows:
The "m" means that its a minimum value, the "x" mark means the maximum value. (taken from the concept of css media querries)

```
<m:{name}:m{width}*x{height}:{width}:{heigth}:{left}:{top}>
```

It can be identified via the responsive quarry.
```
<{tag}:@{name}>{text}</{tag}>
```

It can also reference the sizes of the quarry.
```
<{tag}:@{name}:@{name}:@{name}:@{name}>{text}</{tag}>
```

### Identifiers

The plan is to use css selector like identifiers
How its implemented from css side, is up to them, this tag should only receive basic styling (and animations).
The fancier elements should be in the text.
```
<{tag}:#{idname}:{width}:{heigth}:{left}:{top}>{text}</{tag}>
<{tag}:.{classname}:{width}:{heigth}:{left}:{top}>{text}</{tag}>
```



## Text and other content

Text is a "secondary" member. 
Which means that it can only sit inside primary members, and at the current state of the concept,
it will only render if its parent has no responsive child elements.

Because text is a secondary member, formatting can be more free without breaking the responsive nature of its parents. 
Which means that all the currently existing css formatting methods should work for it, without breaking anything.
(it could even render outside its parent)

The current concept is that the syntax would be like bbcode, to mark its secondary nature, and distinguish it from the main elements.
