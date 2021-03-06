package application;

public class _SharedFontSelData {
	private int dialog;
	private int ok;
	private int cancel;
	private int encoding_pulldown;
	private int encoding_menu;
	private Object list;
	private int name;
	private int sample;
	private byte[][] names;
	private int num;
	private Object sel;
	private Object in_pixels;
	private Byte font_name;
	private Object old;
	private Object old_list;
	private Object exit;
	
	public _SharedFontSelData(int dialog, int ok, int cancel, int encoding_pulldown, int encoding_menu, Object list, int name, int sample, byte[][] names, int num, Object sel, Object in_pixels, Byte font_name, Object old, Object old_list, Object exit) {
		setDialog(dialog);
		setOk(ok);
		setCancel(cancel);
		setEncoding_pulldown(encoding_pulldown);
		setEncoding_menu(encoding_menu);
		setList(list);
		setName(name);
		setSample(sample);
		setNames(names);
		setNum(num);
		setSel(sel);
		setIn_pixels(in_pixels);
		setFont_name(font_name);
		setOld(old);
		setOld_list(old_list);
		setExit(exit);
	}
	public _SharedFontSelData() {
	}
	
	public Byte fn(int i) {
		int generatedNum = this.getNum();
		if (generatedNum < /* Assertion checks: */0) {
			/*Error: Function owner not recognized*//*Error: Function owner not recognized*/abort();
		} 
		if (i >= generatedNum) {
			i = generatedNum - 1;
		} 
		if (i < 0) {
			i = 0;
		} 
		byte[][] generatedNames = this.getNames();
		return generatedNames[i/*
		 * Get a specific substring from a font name.
		 */];
	}
	public void fill_lists(ListSpecifier fix) {
		byte[][] list = new byte[ListSpecifier.NONE][5000];
		int[] count = new int[ListSpecifier.NONE];
		byte[] buf = new byte[256];
		[] items = new ();
		int i;
		int idx;
		for (idx = (int)ListSpecifier.ENCODING; idx < (int)ListSpecifier.NONE; ++idx) {
			count[idx] = 0;
		}
		if (ListSpecifier.fix != /* First we insert the wild char into every single list. */ListSpecifier.ENCODING) {
			ModernizedCProgram.add_to_list(list[ListSpecifier.ENCODING], ModernizedCProgram.wild, count[ListSpecifier.ENCODING]);
		} 
		if (ListSpecifier.fix != ListSpecifier.NAME) {
			ModernizedCProgram.add_to_list(list[ListSpecifier.NAME], ModernizedCProgram.wild, count[ListSpecifier.NAME]);
		} 
		if (ListSpecifier.fix != ListSpecifier.STYLE) {
			ModernizedCProgram.add_to_list(list[ListSpecifier.STYLE], ModernizedCProgram.wild, count[ListSpecifier.STYLE]);
		} 
		if (ListSpecifier.fix != ) {
			ModernizedCProgram.add_to_list(list[], ModernizedCProgram.wild, count[]);
		} 
		int generatedNum = this.getNum();
		Object generatedIn_pixels = this.getIn_pixels();
		for (i = 0; i < generatedNum && i < 5000; i++) {
			if (ModernizedCProgram.proportional(data.fn(i))) {
				continue;
			} 
			if (ListSpecifier.fix != ListSpecifier.ENCODING && data.match(ListSpecifier.NAME, i) && data.match(ListSpecifier.STYLE, i) && data.match(, i)) {
				ModernizedCProgram.encoding_part(data.fn(i), buf);
				ModernizedCProgram.add_to_list(list[ListSpecifier.ENCODING], buf, count[ListSpecifier.ENCODING]);
			} 
			if (ListSpecifier.fix != ListSpecifier.NAME && data.match(ListSpecifier.ENCODING, i) && data.match(ListSpecifier.STYLE, i) && data.match(, i)) {
				ModernizedCProgram.name_part(data.fn(i), buf);
				ModernizedCProgram.add_to_list(list[ListSpecifier.NAME], buf, count[ListSpecifier.NAME]);
			} 
			if (ListSpecifier.fix != ListSpecifier.STYLE && data.match(ListSpecifier.ENCODING, i) && data.match(ListSpecifier.NAME, i) && data.match(, i)) {
				ModernizedCProgram.style_part(data.fn(i), buf);
				ModernizedCProgram.add_to_list(list[ListSpecifier.STYLE], buf, count[ListSpecifier.STYLE]);
			} 
			if (ListSpecifier.fix !=  && data.match(ListSpecifier.ENCODING, i) && data.match(ListSpecifier.NAME, i) && data.match(ListSpecifier.STYLE, i)) {
				ModernizedCProgram.size_part(data.fn(i), buf, generatedIn_pixels);
				ModernizedCProgram.add_to_list(list[], buf, count[]);
			} 
		}
		int generatedEncoding_pulldown = this.getEncoding_pulldown();
		Object generatedSel = this.getSel();
		int generatedEncoding_menu = this.getEncoding_menu();
		if (ListSpecifier.fix != /*
		     * And now do the preselection in all lists where there was one:
		     */ListSpecifier.ENCODING) {
			 n_items = new ();
			 children = new ();
			int selected_button = 0;
			/*Error: Function owner not recognized*//*Error: Function owner not recognized*/XtVaGetValues(generatedEncoding_pulldown, XmNchildren, children, XmNnumChildren, n_items, ((Object)0));
			for (i = 0; i < count[ListSpecifier.ENCODING]; ++i) {
				int button;
				items[i] = /*Error: Function owner not recognized*/XmStringCreateLocalized(list[ListSpecifier.ENCODING][i]);
				if (i < (int)n_items) {
					/*Error: Function owner not recognized*//*Error: Function owner not recognized*/XtVaSetValues(children[/* recycle old button */i], XmNlabelString, items[i], XmNuserData, i, ((Object)0));
					button = children[i];
				} else {
						button = /*Error: Function owner not recognized*/XtVaCreateManagedWidget(/* create a new button */"button", xmPushButtonGadgetClass, generatedEncoding_pulldown, XmNlabelString, items[i], XmNuserData, i, ((Object)0));
						/*Error: Function owner not recognized*//*Error: Function owner not recognized*/XtAddCallback(button, XmNactivateCallback, ()ModernizedCProgram.encoding_callback, ()data);
						/*Error: Function owner not recognized*//*Error: Function owner not recognized*/XtManageChild(button);
				} 
				if (generatedSel[ListSpecifier.ENCODING]) {
					if (!/*Error: Function owner not recognized*/strcmp(generatedSel[ListSpecifier.ENCODING], list[ListSpecifier.ENCODING][i])) {
						selected_button = button;
					} 
				} 
				/*Error: Function owner not recognized*//*Error: Function owner not recognized*/XtFree(list[ListSpecifier.ENCODING][i]);
			}
			for (i = count[ListSpecifier.ENCODING]; i < (int)n_items; ++/* Destroy all the outstanding menu items.
				 */i) {
				/*Error: Function owner not recognized*//*Error: Function owner not recognized*/XtUnmanageChild(children[i]);
				/*Error: Function owner not recognized*//*Error: Function owner not recognized*/XtDestroyWidget(children[i]);
			}
			if (/* Preserve the current selection visually.
				 */selected_button) {
				/*Error: Function owner not recognized*//*Error: Function owner not recognized*/XtVaSetValues(generatedEncoding_menu, XmNmenuHistory, selected_button, ((Object)0));
			} 
			for (i = 0; i < count[ListSpecifier.ENCODING]; ++i) {
				/*Error: Function owner not recognized*//*Error: Function owner not recognized*/XmStringFree(items[i]);
			}
		} 
		Object generatedList = this.getList();
		for (idx = (int)ListSpecifier.NAME; idx < (int)ListSpecifier.NONE; ++/*
		     * Now loop trough the remaining lists and set them up.
		     */idx) {
			int w;
			if (ListSpecifier.fix == (ListSpecifier)idx) {
				continue;
			} 
			switch ((ListSpecifier)idx) {
			case ListSpecifier.NAME:
					w = generatedList[ListSpecifier.NAME];
					break;
			case ListSpecifier.STYLE:
					w = generatedList[ListSpecifier.STYLE];
					break;
			case :
					w = generatedList[];
					break;
			default:
					w = (int)/* for lint */0;
			}
			for (i = 0; i < count[idx]; ++i) {
				items[i] = /*Error: Function owner not recognized*/XmStringCreateLocalized(list[idx][i]);
				/*Error: Function owner not recognized*//*Error: Function owner not recognized*/XtFree(list[idx][i]);
			}
			/*Error: Function owner not recognized*//*Error: Function owner not recognized*/XmListDeleteAllItems(w);
			/*Error: Function owner not recognized*//*Error: Function owner not recognized*/XmListAddItems(w, items, count[idx], 1);
			if (generatedSel[idx]) {
				/*Error: Function owner not recognized*//*Error: Function owner not recognized*/XmStringFree(items[0]);
				items[0] = /*Error: Function owner not recognized*/XmStringCreateLocalized(generatedSel[idx]);
				/*Error: Function owner not recognized*//*Error: Function owner not recognized*/XmListSelectItem(w, items[0], False);
				/*Error: Function owner not recognized*//*Error: Function owner not recognized*/XmListSetBottomItem(w, items[0]);
			} 
			for (i = 0; i < count[idx]; ++i) {
				/*Error: Function owner not recognized*//*Error: Function owner not recognized*/XmStringFree(items[i]);
			}
		}
	}
	public void stoggle_callback(int w, Object call_data) {
		int i;
		int do_sel;
		byte[] newSize = new byte[256];
		 str = new ();
		if (call_data.getReason() != (int)XmCR_VALUE_CHANGED) {
			return /*Error: Unsupported expression*/;
		} 
		Object generatedSel = this.getSel();
		do_sel = (generatedSel[] != ((Object)0)) && /*Error: Function owner not recognized*/strcmp(generatedSel[], ModernizedCProgram.wild);
		int generatedNum = this.getNum();
		Object generatedIn_pixels = this.getIn_pixels();
		for (i = 0; do_sel && (i < generatedNum); i++) {
			if (data.match(ListSpecifier.ENCODING, i) && data.match(ListSpecifier.NAME, i) && data.match(ListSpecifier.STYLE, i) && data.match(, i)) {
				ModernizedCProgram.size_part(data.fn(i), newSize, !generatedIn_pixels);
				break;
			} 
		}
		this.setIn_pixels(!generatedIn_pixels);
		if (generatedSel[]) {
			/*Error: Function owner not recognized*//*Error: Function owner not recognized*/XtFree(generatedSel[]);
		} 
		generatedSel[] = ((Object)0);
		data.fill_lists(ListSpecifier.NONE);
		Object generatedList = this.getList();
		if (do_sel) {
			str = /*Error: Function owner not recognized*/XmStringCreateLocalized(newSize);
			/*Error: Function owner not recognized*//*Error: Function owner not recognized*/XmListSelectItem(generatedList[], str, True);
			/*Error: Function owner not recognized*//*Error: Function owner not recognized*/XmListSetBottomItem(generatedList[], str);
			/*Error: Function owner not recognized*//*Error: Function owner not recognized*/XmStringFree(str/*
			 * Show the currently selected font in the sample text label.
			 */);
		} 
	}
	public void display_sample() {
		[] args = new ();
		int n;
		 font = new ();
		 font_list = new ();
		int display;
		 str = new ();
		int generatedDialog = this.getDialog();
		display = /*Error: Function owner not recognized*/XtDisplay(generatedDialog);
		Byte generatedFont_name = this.getFont_name();
		font = /*Error: Function owner not recognized*/XLoadQueryFont(display, generatedFont_name);
		font_list = ModernizedCProgram.gui_motif_create_fontlist(font);
		n = 0;
		str = /*Error: Function owner not recognized*/XmStringCreateLocalized("AaBbZzYy 0123456789");
		/*Error: Function owner not recognized*//*Error: Function owner not recognized*/XtSetArg(args[n], XmNlabelString, str);
		n++;
		/*Error: Function owner not recognized*//*Error: Function owner not recognized*/XtSetArg(args[n], XmNfontList, font_list);
		n++;
		int generatedSample = this.getSample();
		/*Error: Function owner not recognized*//*Error: Function owner not recognized*/XtSetValues(generatedSample, args, n);
		/*Error: Function owner not recognized*//*Error: Function owner not recognized*/XmStringFree(str);
		Object generatedOld = this.getOld();
		Object generatedOld_list = this.getOld_list();
		if (generatedOld) {
			/*Error: Function owner not recognized*//*Error: Function owner not recognized*/XFreeFont(display, generatedOld);
			/*Error: Function owner not recognized*//*Error: Function owner not recognized*/XmFontListFree(generatedOld_list);
		} 
		this.setOld(font);
		this.setOld_list(font_list);
	}
	public Object do_choice(int w, Object call_data, ListSpecifier which) {
		byte sel;
		/*Error: Function owner not recognized*//*Error: Function owner not recognized*/XmStringGetLtoR(call_data.getItem(), XmSTRING_DEFAULT_CHARSET, sel);
		Object generatedSel = this.getSel();
		if (!generatedSel[ListSpecifier.which]) {
			generatedSel[ListSpecifier.which] = /*Error: Function owner not recognized*/XtNewString(sel);
		} else {
				if (!/*Error: Function owner not recognized*/strcmp(generatedSel[ListSpecifier.which], sel)) {
					/*Error: Function owner not recognized*//*Error: Function owner not recognized*/XtFree(generatedSel[/* unselecting current selection */ListSpecifier.which]);
					generatedSel[ListSpecifier.which] = ((Object)0);
					if (w) {
						/*Error: Function owner not recognized*//*Error: Function owner not recognized*/XmListDeselectItem(w, call_data.getItem());
					} 
				} else {
						/*Error: Function owner not recognized*//*Error: Function owner not recognized*/XtFree(generatedSel[ListSpecifier.which]);
						generatedSel[ListSpecifier.which] = /*Error: Function owner not recognized*/XtNewString(sel);
				} 
		} 
		/*Error: Function owner not recognized*//*Error: Function owner not recognized*/XtFree(sel);
		data.fill_lists(ListSpecifier.which);
		Byte generatedFont_name = this.getFont_name();
		int generatedNum = this.getNum();
		int generatedName = this.getName();
		int generatedSample = this.getSample();
		if (generatedSel[/* If there is a font selection, we display it. */ListSpecifier.ENCODING] && generatedSel[ListSpecifier.NAME] && generatedSel[ListSpecifier.STYLE] && generatedSel[] && /*Error: Function owner not recognized*/strcmp(generatedSel[ListSpecifier.ENCODING], ModernizedCProgram.wild) && /*Error: Function owner not recognized*/strcmp(generatedSel[ListSpecifier.NAME], ModernizedCProgram.wild) && /*Error: Function owner not recognized*/strcmp(generatedSel[ListSpecifier.STYLE], ModernizedCProgram.wild) && /*Error: Function owner not recognized*/strcmp(generatedSel[], ModernizedCProgram.wild)) {
			int i;
			if (generatedFont_name) {
				/*Error: Function owner not recognized*//*Error: Function owner not recognized*/XtFree(generatedFont_name);
			} 
			this.setFont_name(((Object)0));
			for (i = 0; i < generatedNum; i++) {
				if (data.match(ListSpecifier.ENCODING, i) && data.match(ListSpecifier.NAME, i) && data.match(ListSpecifier.STYLE, i) && data.match(, i)) {
					this.setFont_name(/*Error: Function owner not recognized*/XtNewString(data.fn(i)));
					break;
				} 
			}
			if (generatedFont_name) {
				/*Error: Function owner not recognized*//*Error: Function owner not recognized*/XmTextSetString(generatedName, generatedFont_name);
				data.display_sample();
			} else {
					ModernizedCProgram.do_dialog(1, (char_u)((byte)("Error")), (char_u)((byte)("Invalid font specification")), (char_u)((byte)("&Dismiss")), 1, ((Object)0), 0);
			} 
			return True;
		} else {
				int n;
				 str = new ();
				[] args = new ();
				byte nomatch_msg = ((byte)("no specific match"));
				n = 0;
				str = /*Error: Function owner not recognized*/XmStringCreateLocalized(nomatch_msg);
				/*Error: Function owner not recognized*//*Error: Function owner not recognized*/XtSetArg(args[n], XmNlabelString, str);
				++n;
				/*Error: Function owner not recognized*//*Error: Function owner not recognized*/XtSetValues(generatedSample, args, n);
				ModernizedCProgram.gui_motif_menu_fontlist(generatedSample);
				/*Error: Function owner not recognized*//*Error: Function owner not recognized*/XmTextSetString(generatedName, nomatch_msg);
				/*Error: Function owner not recognized*//*Error: Function owner not recognized*/XmStringFree(str);
				return False;
		} 
	}
	public void encoding_callback(int w, Object dummy) {
		 str = new ();
		 fake_data = new ();
		/*Error: Function owner not recognized*//*Error: Function owner not recognized*/XtVaGetValues(w, XmNlabelString, str, ((Object)0));
		if (!str) {
			return /*Error: Unsupported expression*/;
		} 
		fake_data.setItem(str);
		data.do_choice(0, fake_data, ListSpecifier.ENCODING);
	}
	/*
	 * Parse through the fontlist data and set up the three scroll lists.  The fix
	 * parameter can be used to exclude a list from any changes.  This is used for
	 * updates after selections caused by the users actions.
	 */
	public void name_callback(int w, Object call_data) {
		data.do_choice(w, call_data, ListSpecifier.NAME);
	}
	public void style_callback(int w, Object call_data) {
		data.do_choice(w, call_data, ListSpecifier.STYLE);
	}
	public void size_callback(int w, Object call_data) {
		data.do_choice(w, call_data, );
	}
	public void cancel_callback(int w, Object call_data) {
		Object generatedSel = this.getSel();
		if (generatedSel[ListSpecifier.ENCODING]) {
			/*Error: Function owner not recognized*//*Error: Function owner not recognized*/XtFree(generatedSel[ListSpecifier.ENCODING]);
			generatedSel[ListSpecifier.ENCODING] = ((Object)0);
		} 
		if (generatedSel[ListSpecifier.NAME]) {
			/*Error: Function owner not recognized*//*Error: Function owner not recognized*/XtFree(generatedSel[ListSpecifier.NAME]);
			generatedSel[ListSpecifier.NAME] = ((Object)0);
		} 
		if (generatedSel[ListSpecifier.STYLE]) {
			/*Error: Function owner not recognized*//*Error: Function owner not recognized*/XtFree(generatedSel[ListSpecifier.STYLE]);
			generatedSel[ListSpecifier.STYLE] = ((Object)0);
		} 
		if (generatedSel[]) {
			/*Error: Function owner not recognized*//*Error: Function owner not recognized*/XtFree(generatedSel[]);
			generatedSel[] = ((Object)0);
		} 
		Byte generatedFont_name = this.getFont_name();
		if (generatedFont_name) {
			/*Error: Function owner not recognized*//*Error: Function owner not recognized*/XtFree(generatedFont_name);
		} 
		this.setFont_name(((Object)0));
		this.setNum(0);
		byte[][] generatedNames = this.getNames();
		/*Error: Function owner not recognized*//*Error: Function owner not recognized*/XFreeFontNames(generatedNames);
		this.setNames(((Object)0));
		this.setExit(True);
	}
	public void ok_callback(int w, Object call_data) {
		byte pattern;
		byte name;
		int i;
		int generatedName = this.getName();
		pattern = /*Error: Function owner not recognized*/XmTextGetString(generatedName);
		int generatedDialog = this.getDialog();
		name = /*Error: Function owner not recognized*/XListFonts(/*Error: Function owner not recognized*/XtDisplay(generatedDialog), pattern, 1, i);
		/*Error: Function owner not recognized*//*Error: Function owner not recognized*/XtFree(pattern);
		Byte generatedFont_name = this.getFont_name();
		Object generatedSel = this.getSel();
		byte[][] generatedNames = this.getNames();
		if (i != 1) {
			ModernizedCProgram.do_dialog(1, (char_u)((byte)("Error")), (char_u)((byte)("Invalid font specification")), (char_u)((byte)("&Dismiss")), 1, ((Object)0), 0);
			/*Error: Function owner not recognized*//*Error: Function owner not recognized*/XFreeFontNames(name);
		} else {
				if (generatedFont_name) {
					/*Error: Function owner not recognized*//*Error: Function owner not recognized*/XtFree(generatedFont_name);
				} 
				this.setFont_name(/*Error: Function owner not recognized*/XtNewString(name[0]));
				if (generatedSel[ListSpecifier.ENCODING]) {
					/*Error: Function owner not recognized*//*Error: Function owner not recognized*/XtFree(generatedSel[ListSpecifier.ENCODING]);
					generatedSel[ListSpecifier.ENCODING] = ((Object)0);
				} 
				if (generatedSel[ListSpecifier.NAME]) {
					/*Error: Function owner not recognized*//*Error: Function owner not recognized*/XtFree(generatedSel[ListSpecifier.NAME]);
					generatedSel[ListSpecifier.NAME] = ((Object)0);
				} 
				if (generatedSel[ListSpecifier.STYLE]) {
					/*Error: Function owner not recognized*//*Error: Function owner not recognized*/XtFree(generatedSel[ListSpecifier.STYLE]);
					generatedSel[ListSpecifier.STYLE] = ((Object)0);
				} 
				if (generatedSel[]) {
					/*Error: Function owner not recognized*//*Error: Function owner not recognized*/XtFree(generatedSel[]);
					generatedSel[] = ((Object)0);
				} 
				/*Error: Function owner not recognized*//*Error: Function owner not recognized*/XFreeFontNames(name);
				this.setNum(0);
				/*Error: Function owner not recognized*//*Error: Function owner not recognized*/XFreeFontNames(generatedNames);
				this.setNames(((Object)0));
				this.setExit(True/*
				 * Returns pointer to an ASCII character string that contains the name of the
				 * selected font (in X format for naming fonts); it is the users responsibility
				 * to free the space allocated to this string.
				 */);
		} 
	}
	public int getDialog() {
		return dialog;
	}
	public void setDialog(int newDialog) {
		dialog = newDialog;
	}
	public int getOk() {
		return ok;
	}
	public void setOk(int newOk) {
		ok = newOk;
	}
	public int getCancel() {
		return cancel;
	}
	public void setCancel(int newCancel) {
		cancel = newCancel;
	}
	public int getEncoding_pulldown() {
		return encoding_pulldown;
	}
	public void setEncoding_pulldown(int newEncoding_pulldown) {
		encoding_pulldown = newEncoding_pulldown;
	}
	public int getEncoding_menu() {
		return encoding_menu;
	}
	public void setEncoding_menu(int newEncoding_menu) {
		encoding_menu = newEncoding_menu;
	}
	public Object getList() {
		return list;
	}
	public void setList(Object newList) {
		list = newList;
	}
	public int getName() {
		return name;
	}
	public void setName(int newName) {
		name = newName;
	}
	public int getSample() {
		return sample;
	}
	public void setSample(int newSample) {
		sample = newSample;
	}
	public byte[][] getNames() {
		return names;
	}
	public void setNames(byte[][] newNames) {
		names = newNames;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int newNum) {
		num = newNum;
	}
	public Object getSel() {
		return sel;
	}
	public void setSel(Object newSel) {
		sel = newSel;
	}
	public Object getIn_pixels() {
		return in_pixels;
	}
	public void setIn_pixels(Object newIn_pixels) {
		in_pixels = newIn_pixels;
	}
	public Byte getFont_name() {
		return font_name;
	}
	public void setFont_name(Byte newFont_name) {
		font_name = newFont_name;
	}
	public Object getOld() {
		return old;
	}
	public void setOld(Object newOld) {
		old = newOld;
	}
	public Object getOld_list() {
		return old_list;
	}
	public void setOld_list(Object newOld_list) {
		old_list = newOld_list;
	}
	public Object getExit() {
		return exit;
	}
	public void setExit(Object newExit) {
		exit = newExit;
	}
}
