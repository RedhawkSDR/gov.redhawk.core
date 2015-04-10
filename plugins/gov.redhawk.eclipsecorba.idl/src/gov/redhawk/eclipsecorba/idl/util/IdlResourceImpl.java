/** 
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */

// BEGIN GENERATED CODE
package gov.redhawk.eclipsecorba.idl.util;

import gov.redhawk.eclipsecorba.idl.Specification;
import gov.redhawk.eclipsecorba.idl.internal.parser.IDLParser;
import gov.redhawk.eclipsecorba.idl.internal.parser.ParseException;
import gov.redhawk.eclipsecorba.idl.internal.parser.Scope;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.anarres.cpp.Feature;
import org.anarres.cpp.LexerException;
import org.anarres.cpp.Macro;
import org.anarres.cpp.Preprocessor;
import org.anarres.cpp.PreprocessorListener;
import org.anarres.cpp.Source;
import org.anarres.cpp.StringLexerSource;
import org.anarres.cpp.Token;
import org.anarres.cpp.VirtualFile;
import org.anarres.cpp.Warning;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;

/**
 * <!-- begin-user-doc --> The <b>Resource </b> associated with the package.
 * <!-- end-user-doc -->
 * @see gov.redhawk.eclipsecorba.idl.util.IdlResourceFactoryImpl
 * @generated
 */
public class IdlResourceImpl extends ResourceImpl {

	/**
	 * Creates an instance of the resource.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param uri the URI of the new resource.
	 * @generated
	 */
	public IdlResourceImpl(URI uri) {
		super(uri);
	}

	public static final String INCLUDE_PATHS = "gov.redhawk.eclipsecorba.idl.includePaths";
	/**
	 * @since 3.0
	 */
	public static final String ROOT_SCOPE = "gov.redhawk.eclipsecorba.idl.rootScope";
	private static final String MACROS = "gov.redhawk.eclipsecorba.idl.macros";
	private static final String FEATURES = "gov.redhawk.eclipsecorba.idl.features";
	private List<URI> includePaths;

	private Scope rootScope;
	private Map< ? , ? > options;

	@SuppressWarnings("unchecked")
	@Override
	protected void doLoad(final InputStream inputStream, final Map< ? , ? > loadOptions) throws IOException {
		this.options = loadOptions != null ? loadOptions : Collections.EMPTY_MAP;
		final Object tmpScope = this.options.get(IdlResourceImpl.ROOT_SCOPE);
		if (tmpScope instanceof Scope) {
			this.rootScope = (Scope) tmpScope;
		} else {
			this.rootScope = new Scope();
			getResourceSet().getLoadOptions().put(IdlResourceImpl.ROOT_SCOPE, this.rootScope);
		}

		final Object obj = this.options.get(IdlResourceImpl.INCLUDE_PATHS);

		this.includePaths = Collections.emptyList();
		if (obj instanceof List< ? >) {
			this.includePaths = (List<URI>) obj;
		} else if (obj instanceof URI) {
			this.includePaths = Collections.singletonList((URI) obj);
		}
		InputStream newStream;
		try {
			newStream = preprocess(inputStream);
		} catch (final LexerException e) {
			IdlResourceParseException resourceException;
			if (e.getCause() instanceof IdlResourceParseException) {
				resourceException = (IdlResourceParseException) e.getCause();
			} else {
				resourceException = new IdlResourceParseException(e);
			}
			throw resourceException;
		}
		Specification spec = null;
		try {
			TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(getResourceSet());
			if (editingDomain != null) {
				final Specification [] retVal = new Specification[1];
				final InputStream parseStream = newStream;
				final IdlResourceParseException [] exception= new IdlResourceParseException[1];
				editingDomain.getCommandStack().execute(new AbstractCommand() {

					@Override
					protected boolean prepare() {
						return true;
					}

					public void execute() {
						try {
	                        retVal[0] = IDLParser.parse(parseStream, IdlResourceImpl.this, rootScope);
                        } catch (ParseException e) {
                        	exception[0] = new IdlResourceParseException(e);
                			getErrors().add(exception[0]);
                        }
					}
					
					@Override
					public boolean canUndo() {
					    return false;
					}

					public void redo() {
						// TODO Auto-generated method stub

					}

				});
				if (exception[0] != null) {
					throw exception[0];
				}
				spec = retVal[0];
			} else {
				spec = IDLParser.parse(newStream, this, this.rootScope);
			}

			if (spec != null) {
				getContents().add(spec);
			}
		} catch (final ParseException e) {
			final IdlResourceParseException exception = new IdlResourceParseException(e);
			getErrors().add(exception);
			throw exception;
		}

	}

	private InputStream preprocess(final InputStream inputStream) throws IOException, LexerException {

		final ByteArrayOutputStream output = new ByteArrayOutputStream();
		final PrintWriter out = new PrintWriter(new OutputStreamWriter(output));

		final Preprocessor pp = new Preprocessor() {
			@Override
			protected void pragma(final Token name, final List<Token> value) throws IOException, LexerException {
				final StringBuilder builder = new StringBuilder();
				builder.append("#pragma ");
				builder.append(name.getText());
				for (final Token t : value) {
					builder.append(t.getText());
				}
				out.println(builder.toString());
			}

			@Override
			protected boolean include(final VirtualFile file) throws IOException, LexerException {
				if (!file.isFile()) {
					return false;
				}
				URI tmpUri = null;
				if (file instanceof FileStoreVirtualFile) {
					tmpUri = URI.createURI(((FileStoreVirtualFile) file).getStore().toURI().toString());
				}
				if (tmpUri != null) {
					final Resource resource = getResourceSet().getResource(tmpUri, true);
					resource.load(getResourceSet().getLoadOptions());
					return resource.isLoaded();
				} else {
					return super.include(file);
				}
			}
		};

		Map<String, Macro> macros = (Map<String, Macro>) this.options.get(IdlResourceImpl.MACROS);
		if (macros == null) {
			macros = createDefaultMacros();
		}
		for (final Macro m : macros.values()) {
			pp.addMacro(m);
		}

		Set<Feature> features = (Set<Feature>) this.options.get(IdlResourceImpl.FEATURES);
		if (features == null) {
			features = createDefaultFeatures();
		}
		for (final Feature f : features) {
			pp.addFeature(f);
		}

		pp.addWarning(Warning.IMPORT);
		pp.setListener(new PreprocessorListener() {
			@Override
			public void handleError(final Source source, final int line, final int column, final String msg) throws LexerException {
				final IdlResourceParseException exception = new IdlResourceParseException(msg, column, line, source.toString());
				IdlResourceImpl.this.getErrors().add(exception);
				throw new LexerException("Error at " + line + ":" + column + ": " + msg, exception);
			}

			@Override
			public void handleWarning(final Source source, final int line, final int column, final String msg) throws LexerException {
				final IdlResourceParseException exception = new IdlResourceParseException(msg, column, line, source.toString());
				exception.fillInStackTrace();
				IdlResourceImpl.this.getWarnings().add(exception);
			}

			@Override
			public void handleSourceChange(Source source, String event) {
			}
		});

		try {
			pp.setFileSystem(new FileStoreVirtualFileSystem(EFS.getStore(new java.net.URI(getURI().toString())).getFileSystem()));
		} catch (final CoreException e1) {
			throw new IdlResourceParseException(e1);
		} catch (final URISyntaxException e1) {
			throw new IdlResourceParseException(e1);
		}

		for (final URI uri : this.includePaths) {
			pp.getSystemIncludePath().add(uri.toFileString());
			pp.getQuoteIncludePath().add(uri.toFileString());
		}

		pp.addInput(new UriStoreSource(getURI(), inputStream));

		preprocess(pp, out);
		out.flush();
		return new ByteArrayInputStream(output.toByteArray());
	}

	private Set<Feature> createDefaultFeatures() {
		final Set<Feature> features = new HashSet<Feature>();
		features.add(Feature.DIGRAPHS);
		features.add(Feature.TRIGRAPHS);
		features.add(Feature.KEEPALLCOMMENTS);
		return features;
	}

	private Map<String, Macro> createDefaultMacros() throws LexerException {
		final HashMap<String, Macro> macros = new HashMap<String, Macro>();
		addMacro(createMacro("OMNIORB_NO_IR_CLIENT"), macros);
		addMacro(createMacro("ENABLE_CLIENT_IR_SUPPORT"), macros);
		return macros;
	}

	private void addMacro(final Macro macro, final Map<String, Macro> macros) {
		macros.put(macro.getName(), macro);
	}

	private Macro createMacro(final String name) throws LexerException {
		return createMacro(name, "1");
	}

	private Macro createMacro(final String name, final String value) throws LexerException {
		StringLexerSource s = null;
		try {
			final Macro m = new Macro(name);
			s = new StringLexerSource(value);
			for (;;) {
				final Token tok = s.token();
				if (tok.getType() == Token.EOF) {
					break;
				}
				m.addToken(tok);
			}
			return m;
		} catch (final IOException e) {
			throw new LexerException(e);
		} finally {
			try {
				if (s != null) {
					s.close();
				}
			} catch (IOException e) {
				// PASS
			}
		}
	}

	private void preprocess(final Preprocessor pp, final PrintWriter out) throws IOException, LexerException {
		for (;;) {
			Token tok;
			tok = pp.token();
			if (tok == null) {
				break;
			}
			if (tok.getType() == Token.EOF) {
				break;
			}
			out.print(tok.getText());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doSave(final OutputStream outputStream, final Map< ? , ? > options) throws IOException {
		// PASS
	}

} // IdlResourceImpl
