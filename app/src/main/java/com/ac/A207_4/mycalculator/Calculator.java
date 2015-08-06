package com.ac.A207_4.mycalculator;

import java.text.DecimalFormat;
import java.util.StringTokenizer;

import android.R.interpolator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Calculator extends Activity
{

	private Button[] btn = new Button[10];
	private TextView input, showtext;
	private Button add, sub, mul, div, equal, // 加 ，减，乘，除 ,等于
			sin, cos, tan, ln, log, // 函数
			sqrt, square, fact, back, // 平方根，平方，阶乘，退格
			left, right, point, C, e, π; // 左括号，右括号，点，清除，e，圆周率。
	String str_old; // 保存原来的字符串
	String str_new; // 保存新的字符串

	public double pi = 4 * Math.atan(1);
	public boolean put_begin = true; // 输入控制，true为重新输入，false为接着输入

	public boolean put_lock = true; // true表示正确，可以继续输入；false表示有误，输入被锁定

	public boolean equals_flag = true; // 判断是否是按=之后的输入，true表示输入在=之前，false反之

	private Calc calc = new Calc();

	public void onCreate(Bundle icicle)
	{
		super.onCreate(icicle);
		setContentView(R.layout.activity_calculator);
		input = (TextView) findViewById(R.id.textview);
		showtext = (TextView) findViewById(R.id.showtext);
		btn[0] = (Button) findViewById(R.id.zero);
		btn[1] = (Button) findViewById(R.id.one);
		btn[2] = (Button) findViewById(R.id.two);
		btn[3] = (Button) findViewById(R.id.three);
		btn[4] = (Button) findViewById(R.id.four);
		btn[5] = (Button) findViewById(R.id.five);
		btn[6] = (Button) findViewById(R.id.six);
		btn[7] = (Button) findViewById(R.id.seven);
		btn[8] = (Button) findViewById(R.id.eight);
		btn[9] = (Button) findViewById(R.id.nine);

		equal = (Button) findViewById(R.id.equl);
		add = (Button) findViewById(R.id.add);
		sub = (Button) findViewById(R.id.sub);
		mul = (Button) findViewById(R.id.mul);
		div = (Button) findViewById(R.id.div);
		sin = (Button) findViewById(R.id.sin);
		cos = (Button) findViewById(R.id.cos);
		tan = (Button) findViewById(R.id.tan);
		ln = (Button) findViewById(R.id.ln);
		log = (Button) findViewById(R.id.log);
		sqrt = (Button) findViewById(R.id.sqrt);
		square = (Button) findViewById(R.id.square);
		fact = (Button) findViewById(R.id.fact);
		back = (Button) findViewById(R.id.back);
		left = (Button) findViewById(R.id.left);
		right = (Button) findViewById(R.id.right);
		point = (Button) findViewById(R.id.point);
		C = (Button) findViewById(R.id.C);
		e = (Button) findViewById(R.id.e);
		π = (Button) findViewById(R.id.pi);

		// 注册监听
		for (int i = 0; i < 10; ++i)
		{
			btn[i].setOnClickListener(action);
		}
		equal.setOnClickListener(action);
		add.setOnClickListener(action);
		sub.setOnClickListener(action);
		mul.setOnClickListener(action);
		div.setOnClickListener(action);
		sin.setOnClickListener(action);
		cos.setOnClickListener(action);
		tan.setOnClickListener(action);
		ln.setOnClickListener(action);
		log.setOnClickListener(action);
		sqrt.setOnClickListener(action);
		square.setOnClickListener(action);
		fact.setOnClickListener(action);
		back.setOnClickListener(action);
		left.setOnClickListener(action);
		right.setOnClickListener(action);
		point.setOnClickListener(action);
		π.setOnClickListener(action);
		C.setOnClickListener(action);
		e.setOnClickListener(action);
		receive[tip_1] = "#";

	}

	String[] receive = new String[1000]; // 缓存来自键盘的输入字符
	int tip_1 = 0;

	private OnClickListener action = new OnClickListener()
	{

		@Override
		public void onClick(View v)
		{

			String str1 = ((Button) v).getText().toString(); // 键盘获取的字符命令
			String str2 = input.getText().toString(); // 获取显示的字符
			if (str2.equals("出错"))
			{
				input.setText("0");
				showtext.setText("");
				tip_1 = 0;
				put_begin = true;
				equals_flag = true;
			} else
			{

				if (str1.equals("back"))
				{
					if (str2.equals("0"))
					{
					} else
					{
						if (str2.length() == 1)
						{

							input.setText("0");
							showtext.setText("");
							tip_1 = 0;
							put_begin = true;
						} else
						{
							input.setText(str2.substring(0, str2.length() - 1));
							if (tip_1 > 0)
							{
								tip_1--;
							}
						}
					}

				}// ok

				if (str1.equals("C"))
				{
					input.setText("0");
					showtext.setText("");
					tip_1 = 0;
					put_begin = true;
					equals_flag = true;
				} // ok

				if ("0123456789.()sincostanlnloge!+-×÷√^π".indexOf(str1) != -1)
				{
					if ("+-×÷".indexOf(str1) != -1)
					{
						if ("+-×÷".indexOf(str2.substring(str2.length() - 1, str2.length())) != -1)
						{
							input.setText(str2.substring(0, str2.length() - 1));
							print(str1);
							receive[tip_1] = str1;
							equals_flag = true;
						} else
						{
							if (str2.equals("0"))
							{
								put_begin = false;
								tip_1++;
								receive[tip_1] = "0";
								receive[++tip_1] = str1;
								print(str1);
							} else
							{
								tip_1++;
								receive[tip_1] = str1;
								equals_flag = true;
								print(str1);
							}
						}
					}
					if (equals_flag && ("+-×÷".indexOf(str1) == -1))
					{
						if ("sincostanlnlog".indexOf(str1) != -1)
						{

							tip_1++;
							receive[tip_1] = str1;
							print(str1);
							equals_flag = true;
						} else
						{
							if (str2.equals("0"))
							{
								if (str1.equals("0"))
								{
									input.setText("0");
									tip_1 = 0;
									put_begin = true;
									equals_flag = true;
								}
								if (str1.equals("."))
								{
									put_begin = false;
									tip_1++;
									receive[tip_1] = "0";
									receive[++tip_1] = str1;
									print(str1);
								} else
								{
									tip_1++;
									receive[tip_1] = str1;
									print(str1);
								}
							} else
							{
								tip_1++;
								receive[tip_1] = str1;
								print(str1);
							}
						}

					}
					if (!equals_flag && ("+-×÷".indexOf(str1) == -1))
					{
						if ("0123456789!sincostanlnlog()".indexOf(str1) != -1)
						{
							put_begin = true;
							print(str1);
							tip_1 = 0;
							receive[++tip_1] = str1; // 按等号之后再按数字直接变为所按数字
							equals_flag = true;
						} else
						{
							print(str1);
							equals_flag = true; // 按其他按钮直接追加

						}
					}

				}

				else if (str1.equals("=") && equals_flag)
				{
					if (Check(receive))
						calc.process(str2); // 等号之后检查计算
					equals_flag = false;
				}
			}

		}
	};

	private void print(String string)
	{
		// 清屏后输出
		if (put_begin)
			input.setText(string);
		else
			input.append(string); // 在元字符串后追加

		put_begin = false;
	}

	private boolean Check(String[] str)
	{
		int dex = 0, bracket = 0; // 小数点计数和括号匹配
		boolean result = true;
		for (int i = 0; i < tip_1; i++)
		{
			// 下面的运算符不能第一位
			if (str[i].compareTo("#") == 0 && (str[i + 1].compareTo("÷") == 0 || str[i + 1].compareTo("×") == 0 || str[i + 1].compareTo("+") == 0 || str[i + 1].compareTo(")") == 0 || str[i + 1].compareTo("^") == 0||str[i + 1].compareTo("√") == 0))
			{
				input.setText("出错");

				tip_1 = 0;
				return false;
			}
			// 左括号后面直接接右括号,“+x÷”（负号“-”不算）,或者"√^"
			else if (str[i].compareTo("(") == 0 && (str[i + 1].compareTo(")") == 0 || str[i + 1].compareTo(".") == 0 || ("+-×÷".indexOf(str[i + 1]) != -1 && str[i + 1].compareTo("-") != 0) || "√^".indexOf(str[i + 1]) != -1))
			{
				input.setText("出错");

				tip_1 = 0;
				return false;
			}
			// 右括号后面接左括号，数字，“+-x÷sin^...”
			else if (str[i].compareTo(")") == 0 && (str[i + 1].compareTo("(") == 0 || str[i + 1].compareTo(".") == 0 || "0123456789eπ".indexOf(str[i + 1]) != -1 || "sincostanlogln!".indexOf(str[i + 1]) != -1))
			{
				input.setText("出错");

				tip_1 = 0;
				return false;
			}

			// // “.”后面接左括号或者“sincos...”或者连续输入俩个点，报错
			else if (str[i].compareTo(".") == 0 && ("sincostanlogln!".indexOf(str[i + 1]) != -1 || str[i + 1].compareTo(".") == 0 || str[i + 1].compareTo("(") == 0))
			{
				input.setText("出错");

				tip_1 = 0;
				return false;
			}
			// // 数字后面直接接左括号或者“sincos...”
			else if ("0123456789".indexOf(str[i]) != -1 && (str[i + 1].compareTo("(") == 0 || "sincostanlogln!".indexOf(str[i + 1]) != -1))
			{
				input.setText("出错");

				tip_1 = 0;
				return false;
			}
			// “+-x÷”后面直接接右括号，“+-x÷√^”
			else if ("+-×÷".indexOf(str[i]) != -1 && (str[i + 1].compareTo(")") == 0 || "^".indexOf(str[i + 1]) != -1))
			{
				input.setText("出错");

				tip_1 = 0;
				return false;
			}
			// “√”后面直接接右括号，“+-x÷√^”以及“sincos...”
			else if (str[i].compareTo("√") == 0 && (str[i + 1].compareTo(")") == 0 || "+-×÷".indexOf(str[i + 1]) != -1 || str[i].compareTo("^") == 0))
			{
				input.setText("出错");

				tip_1 = 0;
				return false;
			} else if (str[i].compareTo("^") == 0 && ("0123456789π".indexOf(str[i]) != -1 || str[i + 1].compareTo("(") == 0 || "sincostanlogln".indexOf(str[i + 1]) != -1 || "e.√".indexOf(str[i + 1]) != -1))
			{
				input.setText("出错");

				tip_1 = 0;
				return false;
			} else if (str[i].compareTo("!") == 0 && (str[i + 1].compareTo("(") == 0 || "sincostanlogln!".indexOf(str[i + 1]) != -1 || "e.√".indexOf(str[i + 1]) != -1))
			{
				input.setText("出错");

				tip_1 = 0;
				return false;

			}
			// “sincos...”后面直接接右括号“+-x÷√^”以及“sincos...”
			else if ("sincostanlogln".indexOf(str[i]) != -1 && (str[i + 1].compareTo(")") == 0 || "+-×÷".indexOf(str[i + 1]) != -1 || "√^".indexOf(str[i + 1]) != -1 || "sincostanlogln!".indexOf(str[i + 1]) != -1))
			{
				input.setText("出错");

				tip_1 = 0;
				return false;
			}
		}
		str_old = input.getText().toString();
		if (!str_old.equals("出错"))
		{

			for (int i = 0; i <= tip_1; i++)
			{
				// 若之前出现一个小数点，则小数点计数加1
				if (str[i].compareTo(".") == 0)
				{
					dex++;
				}
				// 若出现以下几个运算符之一，小数点计数清零
				if (str[i].compareTo("sin") == 0 || str[i].compareTo("cos") == 0 || str[i].compareTo("tan") == 0 || str[i].compareTo("log") == 0 || str[i].compareTo("ln") == 0 || str[i].compareTo("!") == 0 || str[i].compareTo("√") == 0 || str[i].compareTo("^") == 0 || str[i].compareTo("÷") == 0 || str[i].compareTo("×") == 0 || str[i].compareTo("-") == 0 || str[i].compareTo("+") == 0 || str[i].compareTo("(") == 0 || str[i].compareTo(")") == 0 || "0123456789".indexOf(str[i]) != -1)
				{
					dex = 0;
				}
				if (i == tip_1)
				{
					dex = 0;
				}
			}
			dex++;
			// 若小数点计数大于1，表明小数点重复了
			if (dex > 1)
			{
				input.setText("出错");

				tip_1 = 0;
				return false;
			}

			for (int i = 0; i <= tip_1; i++)
			{
				if (str[i].compareTo("(") == 0)
				{
					bracket++;
				}
				// 如果出现一个右括号，则计数减1
				if (str[i].compareTo(")") == 0)
				{
					bracket--;
				}
			}

			if (bracket != 0)
			{
				input.setText("出错");

				tip_1 = 0;
				return false;
			}

		}
		// 若前一个字符是以下之一，表明=号不合法
		if ("√^sincostanlogln".indexOf(str[tip_1]) != -1)
		{
			input.setText("出错");

			tip_1 = 0;
			return false;
		}
		// 若前一个字符是以下之一，表明=号不合法
		if ("+-×÷".indexOf(str[tip_1]) != -1)
		{
			input.setText("出错");

			tip_1 = 0;
			return false;
		}

		return true;

	}

	public class Calc
	{
		private final int MAX_LEN = 5000;

		public void process(String str)
		{
			// topOp为运算符栈指针，topNumber为操作数栈指针，priorTemp为临时记录运算符优先级
			int topOp = 0, topNumber = 0, priorTemp = 0;

			int priorPlus = 0;

			char[] operator = new char[MAX_LEN]; // 解析表达式时模拟存储运算符栈
			int[] prior = new int[MAX_LEN]; // 和运算符栈相对应，记录相应位置上运算符的优先级
			double[] number = new double[MAX_LEN]; // 解析表达式时模拟存储操作数栈

			char ch, chTemp;

			int flag = 1; // 正负数标志，1表示为正数，-1表示负数

			String num; // 解析表达式字符串时作为被StringTokenizer对象取出的数

			boolean isTrue = true;

			// 将相关sin、cos等替换为单个字符，方便计算
			str = str.replaceAll("sin", "s");
			str = str.replaceAll("cos", "c");
			str = str.replaceAll("tan", "t");
			str = str.replaceAll("log", "g");
			str = str.replaceAll("ln", "l");

			String expression = str.replaceAll("-", "-1×"); // 此expression为最终处理对象
			int pointer = 0; // 取字符游标
			boolean foo = false; // 作为是否出现π的标志，true表示出现，false表示没有出现
			boolean foo2 = false; // 作为是否出现e的标志，true表示出现，false表示没有出现
			double value = 1;
			double value2 = 1;

			StringTokenizer expToken = new StringTokenizer(expression, "+-×÷()sctgl!√^");

			while (pointer < expression.length())
			{
				ch = expression.charAt(pointer);

				if (0 == pointer)
				{
					// 第一个字符为-（负号），此时不带括号
					if (ch == '-')
					{
						flag = -1;
					}
				} else if (expression.charAt(pointer - 1) == '(' && ch == '-')
				{
					flag = -1;
				}
				if (ch <= '9' && ch >= '0' || ch == '.' || ch == 'E' || ch == 'e' || ch == 'π')
				{
					num = expToken.nextToken();
					if (num.indexOf("π") != -1 && num.indexOf("e") != -1)
					{
						showtext.setText("不允许同时出现π和e");
						return;
					}
					if(num.compareTo("π")==0)
					{
						value=pi;
						foo=true;
					}
					// 判断是否存在π
					else if (num.indexOf("π") != -1)
					{
						String[] tmps = num.split("π");
						for (String tmp : tmps)
						{
							if(!tmp.equals(""))
								value *= Double.valueOf(tmp);
						}
						value *= pi;
						foo = true;
					}
					if(num.compareTo("e")==0)
					{
						value2=Math.E;
						foo2=true;
					}
					// 以下判断是否存在e
					else if (num.indexOf("e") != -1)
					{
						String[] tmps2 = num.split("e");
						for (String tmp2 : tmps2)
						{
							if(!tmp2.equals(""))
								value2 *= Double.valueOf(tmp2);
						}
						value2 *= Math.E;
						foo2 = true;
					}
					chTemp = ch;
					// Log.e("guojs", ch + "--->" + i);
					// 取得整个数字
					while (pointer < expression.length() && (chTemp <= '9' && chTemp >= '0' || chTemp == '.' || chTemp == 'e' || chTemp == 'π'))
					{
						chTemp = expression.charAt(pointer++);
						// Log.e("guojs", "i的值为：" + chTemp);
					}
					// 将指针退回之前的位置
					if (pointer >= expression.length())
						pointer -= 1;
					else
					{
						pointer -= 2;
					}
					if (num.compareTo(".") == 0)
						number[topNumber++] = 0;
						// 将正负符号转移给数字
					else
					{
						if (foo)
						{
							number[topNumber++] = value;
						} else if (foo2)
						{
							number[topNumber++] = value2;
						} else
						{
							number[topNumber++] = Double.parseDouble(num) * flag;
						}
						flag = 1;
					}
				}
				// 计算运算符的优先级
				if (ch == '(')
					priorPlus += 4;
				if (ch == ')')
					priorPlus -= 4;
				if (ch == '-' && flag == 1 || ch == '+' || ch == '×' || ch == '÷' || ch == 's' || ch == 'c' || ch == 't' || ch == 'g' || ch == 'l' || ch == '!' || ch == '√' || ch == '^')
				{
					switch (ch)
					{
						// +-的优先级最低，为1
						case '+':
						case '-':
							priorTemp = 1 + priorPlus;
							break;
						// x÷的优先级稍高，为2
						case '×':
						case '÷':
							priorTemp = 2 + priorPlus;
							break;
						// sincos之类优先级为3
						case 's':
						case 'c':
						case 't':
						case 'g':
						case 'l':
						case '!':
							priorTemp = 3 + priorPlus;
							break;
						// 其余优先级为4
						// case '^':
						// case '√':
						default:
							priorTemp = 4 + priorPlus;
							break;
					}
					// 如果当前优先级大于堆栈顶部元素，则直接入栈
					if (topOp == 0 || prior[topOp - 1] < priorTemp)
					{
						prior[topOp] = priorTemp;
						operator[topOp] = ch;
						topOp++;

					}
					// 否则将堆栈中运算符逐个取出，直到当前堆栈顶部运算符的优先级小于当前运算符
					else
					{
						while (topOp > 0 && prior[topOp - 1] >= priorTemp)
						{
							switch (operator[topOp - 1])
							{
								// 取出数字数组的相应元素进行运算
								case '+':
									number[topNumber - 2] += number[topNumber - 1];
									break;
								case '-':
									number[topNumber - 2] -= number[topNumber - 1];
									break;
								case '×':
									number[topNumber - 2] *= number[topNumber - 1];
									break;
								// 判断除数为0的情况
								case '÷':
									if (number[topNumber - 1] == 0)
									{
										isTrue = false;
										return;
									}
									number[topNumber - 2] /= number[topNumber - 1];
									break;
								case '√':
									if (number[topNumber - 1] == 0 || (number[topNumber - 2] < 0 && number[topNumber - 1] % 2 == 0))
									{
										isTrue = false;
										return;
									}
									number[topNumber - 2] = Math.pow(number[topNumber - 2], 1 / number[topNumber - 1]);
									break;
								case '^':
									number[topNumber - 2] = Math.pow(number[topNumber - 2], number[topNumber - 1]);
									break;
								// sin
								case 's':
									number[topNumber - 1] = Math.sin((number[topNumber - 1] / 180) * pi);
									topNumber++;
									break;
								// cos
								case 'c':
								{
									number[topNumber - 1] = Math.cos((number[topNumber - 1] / 180) * pi);
								}
								topNumber++;
								break;
								// tan
								case 't':
								{
									if (number[topNumber - 1] == 90)
									{
										input.setText("∞");
										return;
									} else
									{
										if ((Math.abs(number[topNumber - 1]) / (pi / 2)) % 2 == 1)
										{
											isTrue = false;
											return;
										}
										number[topNumber - 1] = Math.tan((number[topNumber - 1] / 180) * pi);
									}
								}
								topNumber++;
								break;
								// log
								case 'g':
									if (number[topNumber - 1] <= 0)
									{
										isTrue = false;
										return;
									}
									number[topNumber - 1] = Math.log10((number[topNumber - 1] / 180) * pi);
									topNumber++;
									break;
								// ln
								case 'l':
									if(number[topNumber - 1]==0)
									{
										input.setText("-∞");
										tip_1 = 0;
										return;
									}
									else  if (number[topNumber - 1] <= 0)
									{
										isTrue = false;
										return;
									}
									number[topNumber - 1] = Math.log(number[topNumber - 1]);
									topNumber++;
									break;
								// 阶乘
								case '!':
									if (number[topNumber - 1] > 170)
									{
										isTrue = false;
										return;
									} else if (number[topNumber - 1] < 0)
									{
										isTrue = false;
										return;
									}
									number[topNumber - 1] = N(number[topNumber - 1]);
									topNumber++;
									break;
							}
							// 继续取堆栈的下一个元素进行判断
							topNumber--;
							topOp--;
						}
						// 将运算符如堆栈
						prior[topOp] = priorTemp;
						operator[topOp] = ch;
						topOp++;
					}
				}
				pointer++;
			}

			// 依次取出堆栈的运算符进行运算
			while (topOp > 0)
			{
				// +-x直接将数组的后两位数取出运算
				switch (operator[topOp - 1])
				{
					case '+':
						number[topNumber - 2] += number[topNumber - 1];
						break;
					case '-':
						number[topNumber - 2] -= number[topNumber - 1];
						break;
					case '×':
						number[topNumber - 2] *= number[topNumber - 1];
						break;
					// 涉及到除法时要考虑除数不能为零的情况
					case '÷':
						if (number[topNumber - 1] == 0)
						{
							isTrue = false;
							break;
						}
						number[topNumber - 2] /= number[topNumber - 1];
						break;
					case '√':
						if (number[topNumber - 1] == 0 || (number[topNumber - 2] < 0 && number[topNumber - 1] % 2 == 0))
						{
							isTrue = false;
							return;
						}
						number[topNumber - 2] = Math.pow(number[topNumber - 2], 1 / number[topNumber - 1]);
						break;
					case '^':
						number[topNumber - 2] = Math.pow(number[topNumber - 2], number[topNumber - 1]);
						break;
					// sin
					case 's':
					{
						number[topNumber - 1] = Math.sin((number[topNumber - 1] / 180) * pi);
					}
					topNumber++;
					break;
					// cos
					case 'c':
					{
						number[topNumber - 1] = Math.cos((number[topNumber - 1] / 180) * pi);
					}
					topNumber++;
					break;
					// tan
					case 't':
					{
						if (number[topNumber - 1] == 90)
						{
							input.setText("∞");
							tip_1 = 0;
							return;
						} else
						{
							if ((Math.abs(number[topNumber - 1]) / (pi / 2)) % 2 == 1)
							{
								isTrue = false;
								return;
							}
							number[topNumber - 1] = Math.tan((number[topNumber - 1] / 180) * pi);
						}
					}
					topNumber++;
					break;
					// 对数log
					case 'g':
						if (number[topNumber - 1] <= 0)
						{
							isTrue = false;
							return;
						}
						number[topNumber - 1] = Math.log10(number[topNumber - 1]);
						topNumber++;
						break;
					// 自然对数ln
					case 'l':
						if(number[topNumber - 1]==0)
						{
							input.setText("-∞");
							tip_1 = 0;
							return;
						}
						else if (number[topNumber - 1] <= 0)
						{
							isTrue = false;
							return;
						}
						number[topNumber - 1] = Math.log(number[topNumber - 1]);
						topNumber++;
						break;
					// 阶乘
					case '!':
						if (number[topNumber - 1] > 170)
						{
							isTrue = false;
							return;
						} else if (number[topNumber - 1] < 0)
						{
							isTrue = false;
							return;
						}
						number[topNumber - 1] = N(number[topNumber - 1]);
						topNumber++;
						break;
				}
				// 取堆栈下一个元素计算
				topNumber--;
				topOp--;
			}
			// 如果是数字太大，提示错误信息
			if (number[0] > 7.3E306)
			{
				isTrue = false;
			}

			if (isTrue == true)
			{
				// 输出最终结果
				str = str.replaceAll("s", "sin");
				str = str.replaceAll("c", "cos");
				str = str.replaceAll("t", "tan");
				str = str.replaceAll("l", "ln");
				str = str.replaceAll("g", "log");
				str = str.replaceAll("1π", "π");
				str = str.replaceAll("1e", "e");
				showtext.setText(str + "=");
				input.setText(String.valueOf(floatCtrl(number[0])));
			} else
			{
				showtext.setText("");
				input.setText("出错！");
			}

		}

		/*
		 * 阶乘算法
		 */
		public double N(double n)
		{
			int i = 0;
			double sum = 1;
			// 依次将小于等于n的值相乘
			for (i = 1; i <= n; i++)
			{
				sum = sum * i;
			}
			return sum;
		}

		public double floatCtrl(double n)
		{
			// NumberFormat format=NumberFormat.getInstance(); //创建一个格式化类f
			// format.setMaximumFractionDigits(18); //设置小数位的格式
			DecimalFormat format = new DecimalFormat("0.##########");
			return Double.parseDouble(format.format(n));
		}

	}

	@Override
	public void onBackPressed() {
		Intent intent=new Intent(Calculator.this,MainActivity.class);
		startActivity(intent);
		finish();
	}
}
